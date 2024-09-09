package br.com.jorgerabellodev.jpark.model.dto;

public record CompanyResponseDTO(Long id,
                                 String name,
                                 String cnpj,
                                 AddressResponseDTO address,
                                 PhoneResponseDTO phone,
                                 int carParkingSpot,
                                 int motorCycleParkingSpot) {
}
