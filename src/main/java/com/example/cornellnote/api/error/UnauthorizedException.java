package com.example.cornellnote.api.error;

public class UnauthorizedException extends ApiException {
  public UnauthorizedException(String message) {
    super("UNAUTHORIZED", message);
  }
}
