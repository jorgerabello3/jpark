package br.com.jorgerabellodev.jpark.service;

import br.com.jorgerabellodev.jpark.exception.VehicleNotFoundException;
import br.com.jorgerabellodev.jpark.model.dto.CompanyResponseDTO;
import br.com.jorgerabellodev.jpark.model.dto.VehicleRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.VehicleResponseDTO;
import br.com.jorgerabellodev.jpark.model.entity.Company;
import br.com.jorgerabellodev.jpark.model.entity.Vehicle;
import br.com.jorgerabellodev.jpark.model.mapper.CompanyMapper;
import br.com.jorgerabellodev.jpark.model.mapper.VehicleMapper;
import br.com.jorgerabellodev.jpark.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository repository;
  private final VehicleMapper mapper;
  private final CompanyService companyService;
  private final CompanyMapper companyMapper;

  public VehicleResponseDTO create(VehicleRequestDTO vehicleRequestDTO) {
    Company company = getCompany(vehicleRequestDTO);
    Vehicle vehicle = mapper.toEntity(vehicleRequestDTO);
    vehicle.setCompany(company);
    Vehicle savedVehicle = repository.save(vehicle);
    log.info("Successfully created vehicle with id {}", savedVehicle.getId());
    return mapper.toResponseDTO(savedVehicle);
  }

  public VehicleResponseDTO findByPlate(String plate) {
    Vehicle vehicle = this.findByVehiclePlate(plate);
    log.info("Successfully retrieved vehicle by plate: {}", plate);
    return mapper.toResponseDTO(vehicle);
  }

  public VehicleResponseDTO update(VehicleRequestDTO vehicleRequestDTO, String plate) {
    Vehicle vehicle = this.findByVehiclePlate(plate);
    Company company = getCompany(vehicleRequestDTO);
    vehicle.setCompany(company);
    Vehicle data = mapper.toEntity(vehicleRequestDTO);

    vehicle.setBrand(Objects.nonNull(data.getBrand()) ? data.getBrand() : vehicle.getBrand());
    vehicle.setModel(Objects.nonNull(data.getModel()) ? data.getModel() : vehicle.getModel());
    vehicle.setColor(Objects.nonNull(data.getColor()) ? data.getColor() : vehicle.getColor());
    vehicle.setPlate(Objects.nonNull(data.getPlate()) ? data.getPlate() : vehicle.getPlate());
    vehicle.setType(Objects.nonNull(data.getType()) ? data.getType() : vehicle.getType());

    Vehicle savedVehicle = repository.save(vehicle);

    log.info("Successfully update vehicle by plate: {}", plate);

    return mapper.toResponseDTO(savedVehicle);
  }

  public void delete(String plate) {
    Vehicle vehicle = this.findByVehiclePlate(plate);
    repository.delete(vehicle);
    log.info("Successfully deleted vehicle by plate: {}", plate);
  }

  private Vehicle findByVehiclePlate(String plate) {
    return repository.findByPlate(plate).orElseThrow(() -> {
      VehicleNotFoundException exception = new VehicleNotFoundException("Vehicle not found");
      log.error("Failed to retrieve vehicle for plate {} {}", plate, exception.getMessage());
      return exception;
    });
  }

  private Company getCompany(VehicleRequestDTO vehicleRequestDTO) {
    CompanyResponseDTO companyResponseDTO = companyService.findById(vehicleRequestDTO.companyId());
    return companyMapper.toEntity(companyResponseDTO);
  }
}
