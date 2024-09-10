package br.com.jorgerabellodev.jpark.service;

import br.com.jorgerabellodev.jpark.exception.VehicleNotFoundException;
import br.com.jorgerabellodev.jpark.model.dto.VehicleRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.VehicleResponseDTO;
import br.com.jorgerabellodev.jpark.model.entity.Vehicle;
import br.com.jorgerabellodev.jpark.model.mapper.VehicleMapper;
import br.com.jorgerabellodev.jpark.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository repository;
  private final VehicleMapper mapper;

  public VehicleResponseDTO create(VehicleRequestDTO vehicleRequestDTO) {
    Vehicle vehicle = mapper.toEntity(vehicleRequestDTO);
    Vehicle savedVehicle = repository.save(vehicle);
    log.info("Successfully created vehicle {}", vehicle);
    return mapper.toResponseDTO(savedVehicle);
  }

  public VehicleResponseDTO findByPlate(String plate) {
    Vehicle vehicle = this.findByVehiclePlate(plate);
    log.info("Successfully retrieved vehicle by plate: {} {}", vehicle, plate);
    return mapper.toResponseDTO(vehicle);
  }

  private Vehicle findByVehiclePlate(String plate) {
    return repository.findByPlate(plate).orElseThrow(() -> {
      VehicleNotFoundException exception = new VehicleNotFoundException("Vehicle not found");
      log.error("Failed to retrieve vehicle for plate {} {}", plate, exception.getMessage());
      return exception;
    });
  }

}
