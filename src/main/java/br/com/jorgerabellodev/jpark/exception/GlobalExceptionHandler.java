package br.com.jorgerabellodev.jpark.exception;

import br.com.jorgerabellodev.jpark.model.dto.ErrorDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorDTO error = ErrorDTO.builder()
            .message(ex.getMostSpecificCause().getLocalizedMessage())
            .status(status)
            .build();
    return new ResponseEntity<>(error, status);
  }


  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    String message = ex.getMessage();
    String errorMessage = extractConstraintViolationMessage(message);

    ErrorDTO error = ErrorDTO.builder()
            .message(errorMessage)
            .status(status)
            .build();
    return new ResponseEntity<>(error, status);
  }


  private String extractConstraintViolationMessage(String message) {
    String regex = "ERROR: duplicate key value violates unique constraint";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(message);

    if (matcher.find()) {
      return matcher.group(0);
    }

    return "Data integrity violation occurred.";
  }

}
