package br.com.jorgerabellodev.jpark.model.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorDTO(String message, HttpStatus status) {
}
