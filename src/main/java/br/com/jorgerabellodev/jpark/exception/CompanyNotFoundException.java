package br.com.jorgerabellodev.jpark.exception;

public class CompanyNotFoundException extends RuntimeException {

  public CompanyNotFoundException(String message) {
    super(message);
  }
}
