package com.example.cornellnote.api.error;

public class NotFoundException extends ApiException {
  public NotFoundException(String message) {
    super("NOT_FOUND", message);
  }
}
