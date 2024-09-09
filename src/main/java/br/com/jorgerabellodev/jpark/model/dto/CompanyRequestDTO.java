package br.com.jorgerabellodev.jpark.model.dto;

public record CompanyRequestDTO(String name,
                                String cnpj,
                                AddressRequestDTO address,
                                PhoneRequestDTO phone,
                                int carParkingSpot,
                                int motorCycleParkingSpot) {
}
