package com.example.cornellnote.api.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record NoteResponse(
    UUID noteId,
    String title,
    String cue,
    String notes,
    String summary,
    UUID notebookId,
    List<TagResponse> tags,
    Instant createdAt,
    Instant updatedAt) {
  public NoteResponse {
    if (tags != null) {
      tags = List.copyOf(tags);
    }
  }
}
