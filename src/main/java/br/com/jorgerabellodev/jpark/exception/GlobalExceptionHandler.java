package br.com.jorgerabellodev.jpark.exception;

import br.com.jorgerabellodev.jpark.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CompanyNotFoundException.class)
  public ResponseEntity<ErrorDTO> handleCompanyNotFoundException(CompanyNotFoundException ex) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorDTO error = ErrorDTO.builder()
            .message(ex.getMessage())
            .status(status)
            .build();
    return new ResponseEntity(error, status);
  }
}
