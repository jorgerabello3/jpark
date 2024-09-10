package br.com.jorgerabellodev.jpark.service;

import br.com.jorgerabellodev.jpark.exception.CompanyNotFoundException;
import br.com.jorgerabellodev.jpark.model.dto.CompanyRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.model.entity.Company;
import br.com.jorgerabellodev.jpark.model.mapper.CompanyMapper;
import br.com.jorgerabellodev.jpark.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyRepository repository;
  private final CompanyMapper mapper;


  public CompanyResponseDTO create(CompanyRequestDTO companyRequestDTO) {
    Company company = mapper.toEntity(companyRequestDTO);
    Company savedCompany = repository.save(company);
    log.info("Successfully created company with id: {}", savedCompany.getId());
    return mapper.toResponseDTO(savedCompany);
  }

  public CompanyResponseDTO findById(Long id) {
    Company company = this.findCompanyById(id);
    log.info("Successfully retrieved company by id: {}", id);
    return mapper.toResponseDTO(company);
  }

  public CompanyResponseDTO update(CompanyRequestDTO companyRequestDTO, Long id) {
    Company company = this.findCompanyById(id);

    Company data = mapper.toEntity(companyRequestDTO);

    company.setName(Objects.nonNull(data.getName()) ? data.getName() : company.getName());
    company.setCnpj(Objects.nonNull(data.getCnpj()) ? data.getCnpj() : company.getCnpj());
    company.setAddress(Objects.nonNull(data.getAddress()) ? data.getAddress() : company.getAddress());
    company.setPhone(Objects.nonNull(data.getPhone()) ? data.getPhone() : company.getPhone());
    company.setCarParkingSpot(data.getCarParkingSpot());
    company.setMotorCycleParkingSpot(data.getMotorCycleParkingSpot());

    Company savedCompany = repository.save(company);

    log.info("Successfully update company by id: {}", id);

    return mapper.toResponseDTO(savedCompany);
  }

  public void delete(Long id) {
    Company company = this.findCompanyById(id);
    repository.delete(company);
    log.info("Successfully deleted company by id: {}", id);
  }

  private Company findCompanyById(Long id) {
    return repository.findById(id).orElseThrow(() -> {
      CompanyNotFoundException exception = new CompanyNotFoundException("Company not found");
      log.error("Failed to retrieve company for id {} {}", id, exception.getMessage());
      return exception;
    });
  }
}
