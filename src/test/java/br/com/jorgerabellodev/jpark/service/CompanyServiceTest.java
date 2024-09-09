package br.com.jorgerabellodev.jpark.service;

import br.com.jorgerabellodev.jpark.exception.CompanyNotFoundException;
import br.com.jorgerabellodev.jpark.model.dto.AddressRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.AddressResponseDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.model.dto.PhoneRequestDTO;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
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

    assertThat(companyResponseDTO)
            .isNotNull();

    verify(mapper).toEntity(any(CompanyRequestDTO.class));
    verify(repository).save(any(Company.class));
    verify(mapper).toResponseDTO(any(Company.class));
  }

  @Test
  @DisplayName("Given an id should return a company by id")
  void given_an_id_should_return_a_company_by_id() {
    Company company = Instancio.ofBlank(Company.class)
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

    when(repository.findById(anyLong())).thenReturn(Optional.of(company));
    when(mapper.toResponseDTO(any(Company.class))).thenReturn(response);

    CompanyResponseDTO companyResponseDTO = service.findById(1L);

    assertThat(companyResponseDTO)
            .isNotNull();

    verify(repository).findById(anyLong());
    verify(mapper).toResponseDTO(any(Company.class));
  }

  @Test
  @DisplayName("Given an id should throw an exception when this id was not found")
  void given_an_id_should_throw_an_exception_when_this_id_was_not_found() {

    when(repository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(CompanyNotFoundException.class, () -> service.findById(1L));

    verify(repository).findById(anyLong());
    verify(mapper, never()).toResponseDTO(any(Company.class));
  }

  @Test
  @DisplayName("Given a company and an existent id should update data")
  void given_a_company_and_an_existent_id_should_update_data() {
    Company foundCompany = Instancio.ofBlank(Company.class)
            .set(field("id"), 1L)
            .set(field("name"), "Paper Inc.")
            .set(field("cnpj"), "86925495000185")
            .set(field("address"), Instancio.create(Address.class))
            .set(field("phone"), Instancio.create(Phone.class))
            .set(field("carParkingSpot"), 200)
            .set(field("motorCycleParkingSpot"), 350)
            .create();
    when(repository.findById(anyLong())).thenReturn(Optional.of(foundCompany));


    CompanyRequestDTO companyRequestDTO = Instancio.ofBlank(CompanyRequestDTO.class)
            .set(field("name"), "Paper and Test Inc.")
            .set(field("cnpj"), "86925495000191")
            .set(field("address"), Instancio.create(AddressRequestDTO.class))
            .set(field("phone"), Instancio.create(PhoneRequestDTO.class))
            .set(field("carParkingSpot"), 12)
            .set(field("motorCycleParkingSpot"), 3)
            .create();

    Company company = Instancio.ofBlank(Company.class)
            .set(field("name"), "Paper and Test Inc.")
            .set(field("cnpj"), "86925495000191")
            .set(field("address"), Instancio.create(Address.class))
            .set(field("phone"), Instancio.create(Phone.class))
            .set(field("carParkingSpot"), 12)
            .set(field("motorCycleParkingSpot"), 3)
            .create();

    when(mapper.toEntity(any(CompanyRequestDTO.class))).thenReturn(company);

    Company savedCompany = Instancio.ofBlank(Company.class)
            .set(field("name"), "Paper and Test Inc.")
            .set(field("cnpj"), "86925495000191")
            .set(field("address"), Instancio.create(Address.class))
            .set(field("phone"), Instancio.create(Phone.class))
            .set(field("carParkingSpot"), 12)
            .set(field("motorCycleParkingSpot"), 3)
            .create();
    when(repository.save(any(Company.class))).thenReturn(savedCompany);

    CompanyResponseDTO response = Instancio.ofBlank(CompanyResponseDTO.class)
            .set(field("name"), "Paper and Test Inc.")
            .set(field("cnpj"), "86925495000191")
            .set(field("address"), Instancio.create(AddressResponseDTO.class))
            .set(field("phone"), Instancio.create(PhoneResponseDTO.class))
            .set(field("carParkingSpot"), 12)
            .set(field("motorCycleParkingSpot"), 3)
            .create();
    when(mapper.toResponseDTO(any(Company.class))).thenReturn(response);

    CompanyResponseDTO result = service.update(companyRequestDTO, 1L);


    assertThat(result).isNotNull();

    assertThat(result.name()).isEqualTo("Paper and Test Inc.");
    assertThat(result.cnpj()).isEqualTo("86925495000191");
    assertThat(result.carParkingSpot()).isEqualTo(12);
    assertThat(result.motorCycleParkingSpot()).isEqualTo(3);

    verify(mapper).toEntity(any(CompanyRequestDTO.class));
    verify(repository).findById(anyLong());
    verify(repository).save(any(Company.class));
    verify(mapper).toResponseDTO(any(Company.class));
  }

  @Test
  @DisplayName("Given a company and an nonexistent id should throw  an exception when this id was not found")
  void given_a_company_and_an_nonexistent_id_should_throw_an_exception_when_this_id_was_not_found() {
    when(repository.findById(anyLong())).thenReturn(Optional.empty());

    CompanyRequestDTO companyRequestDTO = Instancio.ofBlank(CompanyRequestDTO.class)
            .set(field("name"), "Paper and Test Inc.")
            .set(field("cnpj"), "86925495000191")
            .set(field("address"), Instancio.create(AddressRequestDTO.class))
            .set(field("phone"), Instancio.create(PhoneRequestDTO.class))
            .set(field("carParkingSpot"), 12)
            .set(field("motorCycleParkingSpot"), 3)
            .create();

    assertThrows(CompanyNotFoundException.class, () -> service.update(companyRequestDTO, 1L));

    verify(mapper, never()).toEntity(any(CompanyRequestDTO.class));
    verify(repository).findById(anyLong());
    verify(repository, never()).save(any(Company.class));
    verify(mapper, never()).toResponseDTO(any(Company.class));
  }
}