package com.example.cornellnote.api.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record NoteSummaryResponse(
    UUID noteId,
    String title,
    UUID notebookId,
    List<TagResponse> tags,
    String preview,
    Instant createdAt,
    Instant updatedAt) {
  public NoteSummaryResponse {
    if (tags != null) {
      tags = List.copyOf(tags);
    }
  }
}
