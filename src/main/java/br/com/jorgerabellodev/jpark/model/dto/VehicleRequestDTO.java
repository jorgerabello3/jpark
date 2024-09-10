package br.com.jorgerabellodev.jpark.model.dto;

import br.com.jorgerabellodev.jpark.model.entity.VehicleType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record VehicleRequestDTO(
        @NotNull @NotEmpty String brand,
        @NotNull @NotEmpty String model,
        @NotNull @NotEmpty String color,
        @NotNull @NotEmpty String plate,
        @NotNull VehicleType type) {
}
