package com.example.cornellnote.api.dto;

import java.time.Instant;

public record ExportJobResponse(
    String exportId,
    String noteId,
    String status,
    String downloadUrl,
    Instant createdAt,
    Instant updatedAt
) {
}
