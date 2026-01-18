package com.example.cornellnote.api.error;

public class ApiException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private final String code;

  public ApiException(final String code, final String message) {
    super(message);
    this.code = code;
  }

  public ApiException(final String code, final String message, final Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
