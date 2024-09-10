package br.com.jorgerabellodev.jpark.service;

import br.com.jorgerabellodev.jpark.exception.VehicleNotFoundException;
import br.com.jorgerabellodev.jpark.model.dto.VehicleRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.VehicleResponseDTO;
import br.com.jorgerabellodev.jpark.model.entity.Vehicle;
import br.com.jorgerabellodev.jpark.model.entity.VehicleType;
import br.com.jorgerabellodev.jpark.model.mapper.VehicleMapper;
import br.com.jorgerabellodev.jpark.repository.VehicleRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

  @Mock
  private VehicleRepository repository;

  @Mock
  private VehicleMapper mapper;

  @InjectMocks
  private VehicleService service;

  @Test
  @DisplayName("Given a vehicle dto should create a vehicle successfully")
  void given_a_vehicle_dto_should_create_a_vehicle_successfully() {
    VehicleRequestDTO request = Instancio.create(VehicleRequestDTO.class);

    when(mapper.toEntity(any(VehicleRequestDTO.class))).thenReturn(Instancio.create(Vehicle.class));

    Vehicle savedVehicle = Instancio.ofBlank(Vehicle.class)
            .set(field("id"), 1L)
            .set(field("brand"), "Lamborghini")
            .set(field("model"), "Diablo")
            .set(field("color"), "Red")
            .set(field("plate"), "FIX9J22")
            .set(field("type"), VehicleType.CAR)
            .create();
    when(repository.save(any(Vehicle.class))).thenReturn(savedVehicle);

    VehicleResponseDTO response = Instancio.ofBlank(VehicleResponseDTO.class)
            .set(field("id"), 1L)
            .set(field("brand"), "Lamborghini")
            .set(field("model"), "Diablo")
            .set(field("color"), "Red")
            .set(field("plate"), "FIX9J22")
            .set(field("type"), VehicleType.CAR)
            .create();
    when(mapper.toResponseDTO(any(Vehicle.class))).thenReturn(response);

    VehicleResponseDTO vehicleResponseDTO = service.create(request);

    assertThat(vehicleResponseDTO)
            .isNotNull();

    verify(mapper).toEntity(any(VehicleRequestDTO.class));
    verify(repository).save(any(Vehicle.class));
    verify(mapper).toResponseDTO(any(Vehicle.class));
  }

  @Test
  @DisplayName("Given a plate should return a vehicle by plate")
  void given_a_plate_should_return_a_vehicle_by_plate() {

    Vehicle vehicle = Instancio.ofBlank(Vehicle.class)
            .set(field("id"), 1L)
            .set(field("brand"), "Lamborghini")
            .set(field("model"), "Diablo")
            .set(field("color"), "Red")
            .set(field("plate"), "FIX9J22")
            .set(field("type"), VehicleType.CAR)
            .create();
    when(repository.findByPlate(anyString())).thenReturn(Optional.of(vehicle));

    VehicleResponseDTO response = Instancio.ofBlank(VehicleResponseDTO.class)
            .set(field("id"), 1L)
            .set(field("brand"), "Lamborghini")
            .set(field("model"), "Diablo")
            .set(field("color"), "Red")
            .set(field("plate"), "FIX9J22")
            .set(field("type"), VehicleType.CAR)
            .create();
    when(mapper.toResponseDTO(any(Vehicle.class))).thenReturn(response);

    VehicleResponseDTO vehicleResponseDTO = service.findByPlate("FOX8I11");

    assertThat(vehicleResponseDTO)
            .isNotNull();

    verify(repository).findByPlate(anyString());
    verify(mapper).toResponseDTO(any(Vehicle.class));
  }

  @Test
  @DisplayName("Given a plate should throw an exception when this plate was not found")
  void given_a_plate_should_throw_an_exception_when_this_plate_was_not_found() {

    when(repository.findByPlate(anyString())).thenReturn(Optional.empty());

    assertThrows(VehicleNotFoundException.class, () -> service.findByPlate("EIX9J21"));

    verify(repository).findByPlate(anyString());
    verify(mapper, never()).toResponseDTO(any(Vehicle.class));
  }
}
