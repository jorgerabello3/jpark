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

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyRepository repository;
  private final CompanyMapper mapper;


  public CompanyResponseDTO create(CompanyRequestDTO companyRequestDTO) {
    Company company = mapper.toEntity(companyRequestDTO);
    Company savedCompany = repository.save(company);
    log.info("Successfully created company: {}", company);
    return mapper.toResponseDTO(savedCompany);
  }

  public CompanyResponseDTO findById(Long id) {
    Company company = repository.findById(id)
            .orElseThrow(() -> {
              CompanyNotFoundException exception = new CompanyNotFoundException("Company not found");
              log.error("Failed to retrieve company for id {} {}", id, exception.getMessage());
              return exception;
            });
    log.info("Successfully retrieved company by id: {} {}", company, id);
    return mapper.toResponseDTO(company);
  }
}
