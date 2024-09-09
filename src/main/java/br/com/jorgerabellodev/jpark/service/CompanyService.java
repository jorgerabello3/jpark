package br.com.jorgerabellodev.jpark.service;

import br.com.jorgerabellodev.jpark.model.dto.CompanyRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.model.entity.Company;
import br.com.jorgerabellodev.jpark.model.mapper.CompanyMapper;
import br.com.jorgerabellodev.jpark.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyRepository repository;
  private final CompanyMapper mapper;


  public CompanyResponseDTO create(CompanyRequestDTO companyRequestDTO) {
    Company company = mapper.toEntity(companyRequestDTO);
    Company savedCompany = repository.save(company);
    return mapper.toResponseDTO(savedCompany);
  }
}
