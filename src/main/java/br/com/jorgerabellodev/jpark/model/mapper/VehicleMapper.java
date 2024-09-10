package br.com.jorgerabellodev.jpark.model.mapper;

import br.com.jorgerabellodev.jpark.model.dto.VehicleRequestDTO;
import br.com.jorgerabellodev.jpark.model.dto.VehicleResponseDTO;
import br.com.jorgerabellodev.jpark.model.entity.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

  VehicleResponseDTO toResponseDTO(Vehicle vehicle);

  Vehicle toEntity(VehicleRequestDTO request);
}
