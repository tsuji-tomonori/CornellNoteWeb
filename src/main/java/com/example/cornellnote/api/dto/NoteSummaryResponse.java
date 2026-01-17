package com.example.cornellnote.api.dto;

import java.time.Instant;
import java.util.List;

public record NoteSummaryResponse(
    String noteId,
    String title,
    String notebookId,
    List<TagResponse> tags,
    String preview,
    Instant createdAt,
    Instant updatedAt
) {
}
