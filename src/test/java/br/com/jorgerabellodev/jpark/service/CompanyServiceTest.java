package br.com.jorgerabellodev.jpark.service;

import br.com.jorgerabellodev.jpark.model.dto.AddressResponseDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.model.dto.PhoneResponseDTO;
import br.com.jorgerabellodev.jpark.model.entity.Address;
import br.com.jorgerabellodev.jpark.model.entity.Company;
import br.com.jorgerabellodev.jpark.model.entity.Phone;
import br.com.jorgerabellodev.jpark.model.mapper.CompanyMapper;
import br.com.jorgerabellodev.jpark.repository.CompanyRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

  @Mock
  private CompanyRepository repository;

  @Mock
  private CompanyMapper mapper;

  @InjectMocks
  private CompanyService service;

  @Test
  @DisplayName("Given an CompanyDTO should create a company successfully")
  void given_an_company_dto_should_create_a_company_successfully() {
    CompanyRequestDTO request = Instancio.create(CompanyRequestDTO.class);

    when(mapper.toEntity(any(CompanyRequestDTO.class))).thenReturn(Instancio.create(Company.class));

    Company savedCompany = Instancio.ofBlank(Company.class)
            .set(field("id"), 1L)
            .set(field("cnpj"), "86925495000185")
            .set(field("address"), Instancio.create(Address.class))
            .set(field("phone"), Instancio.create(Phone.class))
            .set(field("carParkingSpot"), 200)
            .set(field("motorCycleParkingSpot"), 350)
            .create();

    CompanyResponseDTO response = Instancio.ofBlank(CompanyResponseDTO.class)
            .set(field("id"), 1L)
            .set(field("cnpj"), "86925495000185")
            .set(field("address"), Instancio.create(AddressResponseDTO.class))
            .set(field("phone"), Instancio.create(PhoneResponseDTO.class))
            .set(field("carParkingSpot"), 200)
            .set(field("motorCycleParkingSpot"), 350)
            .create();


    when(repository.save(any(Company.class))).thenReturn(savedCompany);

    when(mapper.toResponseDTO(any(Company.class))).thenReturn(response);

    CompanyResponseDTO companyResponseDTO = service.create(request);

    assertThat(companyResponseDTO).isNotNull();

    verify(mapper).toEntity(any(CompanyRequestDTO.class));
    verify(repository).save(any(Company.class));
    verify(mapper).toResponseDTO(any(Company.class));
  }
}