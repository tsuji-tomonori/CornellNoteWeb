package com.example.cornellnote.api.dto;

import java.time.Instant;
import java.util.UUID;

public record TagResponse(UUID tagId, String name, Instant createdAt, Instant updatedAt) {}
