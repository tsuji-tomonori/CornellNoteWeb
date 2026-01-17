package com.example.cornellnote.api.dto;

import java.time.Instant;

public record TagResponse(
    String tagId,
    String name,
    Instant createdAt,
    Instant updatedAt
) {
}
