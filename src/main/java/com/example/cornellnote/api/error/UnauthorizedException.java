package com.example.cornellnote.api.error;

public class UnauthorizedException extends ApiException {
  private static final long serialVersionUID = 1L;

  public UnauthorizedException(final String message) {
    super("UNAUTHORIZED", message);
  }
}
