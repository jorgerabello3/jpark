package br.com.jorgerabellodev.jpark.model.dto;

public record PhoneRequestDTO(String countryCode,
                              String ddd,
                              String number) {
}
