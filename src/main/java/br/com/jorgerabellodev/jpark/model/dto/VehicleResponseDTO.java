package br.com.jorgerabellodev.jpark.model.dto;

import br.com.jorgerabellodev.jpark.model.entity.VehicleType;

public record VehicleResponseDTO(Long id,
                                 String brand,
                                 String model,
                                 String color,
                                 String plate,
                                 VehicleType type) {
}
