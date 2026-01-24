package com.example.cornellnote.api.dto;

public record ErrorResponse(String code, String message, String traceId) {}
