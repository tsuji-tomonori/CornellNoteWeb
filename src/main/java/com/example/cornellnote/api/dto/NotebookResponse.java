package com.example.cornellnote.api.dto;

import java.time.Instant;

public record NotebookResponse(
    String notebookId,
    String name,
    Instant createdAt,
    Instant updatedAt
) {
}
