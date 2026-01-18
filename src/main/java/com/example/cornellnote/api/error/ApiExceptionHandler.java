package com.example.cornellnote.api.error;

import com.example.cornellnote.api.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
  private static final String CODE_UNAUTHORIZED = "UNAUTHORIZED";
  private static final String CODE_NOT_FOUND = "NOT_FOUND";

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorResponse> handleApiException(
      ApiException ex, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    if (CODE_UNAUTHORIZED.equals(ex.getCode())) {
      status = HttpStatus.UNAUTHORIZED;
    } else if (CODE_NOT_FOUND.equals(ex.getCode())) {
      status = HttpStatus.NOT_FOUND;
    }
    return buildResponse(ex.getCode(), ex.getMessage(), request, status);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    return buildResponse("BAD_REQUEST", "Validation failed", request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnknown(Exception ex, HttpServletRequest request) {
    return buildResponse(
        "INTERNAL_ERROR", "Unexpected error", request, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<ErrorResponse> buildResponse(
      String code, String message, HttpServletRequest request, HttpStatus status) {
    String traceId = request.getHeader("X-Trace-Id");
    if (traceId == null || traceId.isBlank()) {
      traceId = UUID.randomUUID().toString();
    }
    ErrorResponse response = new ErrorResponse(code, message, traceId);
    return ResponseEntity.status(status).header("X-Trace-Id", traceId).body(response);
  }
}
