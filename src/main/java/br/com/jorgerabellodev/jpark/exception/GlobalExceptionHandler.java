package br.com.jorgerabellodev.jpark.exception;

import br.com.jorgerabellodev.jpark.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CompanyNotFoundException.class)
  public ResponseEntity<ErrorDTO> handleCompanyNotFoundException(CompanyNotFoundException ex) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorDTO error = ErrorDTO.builder()
            .message(ex.getMessage())
            .status(status)
            .build();
    return new ResponseEntity<>(error, status);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorDTO error = ErrorDTO.builder()
            .message(Objects.requireNonNull(ex.getDetailMessageArguments())[1].toString())
            .status(status)
            .build();
    return new ResponseEntity<>(error, status);
  }
}
