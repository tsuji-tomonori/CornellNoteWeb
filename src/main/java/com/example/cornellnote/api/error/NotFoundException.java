package com.example.cornellnote.api.error;

public class NotFoundException extends ApiException {
  private static final long serialVersionUID = 1L;

  public NotFoundException(final String message) {
    super("NOT_FOUND", message);
  }
}
