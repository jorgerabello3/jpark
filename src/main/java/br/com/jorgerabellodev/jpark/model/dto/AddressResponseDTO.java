package br.com.jorgerabellodev.jpark.model.dto;

public record AddressResponseDTO(Long id,
                                 String street,
                                 String number,
                                 String neighborhood,
                                 String city,
                                 String state,
                                 String cep) {
}
