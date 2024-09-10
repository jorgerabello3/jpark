package br.com.jorgerabellodev.jpark.model.dto;

public record AddressRequestDTO(String street,
                                String number,
                                String neighborhood,
                                String city,
                                String state,
                                String cep) {
}
