package br.com.jorgerabellodev.jpark.model.dto;

public record PhoneResponseDTO(Long id,
                               String countryCode,
                               String ddd,
                               String number) {
}
