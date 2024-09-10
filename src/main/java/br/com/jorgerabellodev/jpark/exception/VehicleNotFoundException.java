package br.com.jorgerabellodev.jpark.exception;

public class VehicleNotFoundException extends RuntimeException {
  public VehicleNotFoundException(String message) {
    super(message);
  }
}
