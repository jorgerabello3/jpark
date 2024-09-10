package br.com.jorgerabellodev.jpark.model.mapper;

import br.com.jorgerabellodev.jpark.model.dto.CompanyRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.model.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

  CompanyResponseDTO toResponseDTO(Company company);

  Company toEntity(CompanyRequestDTO request);
}
