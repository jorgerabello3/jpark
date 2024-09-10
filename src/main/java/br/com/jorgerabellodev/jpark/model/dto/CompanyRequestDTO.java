package br.com.jorgerabellodev.jpark.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CompanyRequestDTO(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String cnpj,
        @NotNull AddressRequestDTO address,
        @NotNull PhoneRequestDTO phone,
        @NotNull @Min(1) int carParkingSpot,
        @NotNull @Min(1) int motorCycleParkingSpot) {
}
