package com.example.cornellnote.api.error;

public class BadRequestException extends ApiException {
  private static final long serialVersionUID = 1L;

  public BadRequestException(final String message) {
    super("BAD_REQUEST", message);
  }

  public BadRequestException(final String message, final Throwable cause) {
    super("BAD_REQUEST", message, cause);
  }
}
