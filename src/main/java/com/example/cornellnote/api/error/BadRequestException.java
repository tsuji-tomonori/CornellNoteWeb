package com.example.cornellnote.api.error;

public class BadRequestException extends ApiException {
  public BadRequestException(String message) {
    super("BAD_REQUEST", message);
  }
}
