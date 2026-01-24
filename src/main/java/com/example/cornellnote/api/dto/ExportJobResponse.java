package com.example.cornellnote.api.dto;

import java.time.Instant;
import java.util.UUID;

public record ExportJobResponse(
    UUID exportId,
    UUID noteId,
    String status,
    String downloadUrl,
    Instant createdAt,
    Instant updatedAt) {}
