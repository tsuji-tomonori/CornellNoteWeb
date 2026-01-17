package com.example.cornellnote.api.dto;

public record AuthSession(
    String userId,
    String email,
    String displayName
) {
}
