package com.example.cornellnote.api.dto;

import java.util.UUID;

public record AuthSession(UUID userId, String email, String displayName) {}
