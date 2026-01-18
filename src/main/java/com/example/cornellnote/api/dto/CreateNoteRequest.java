package com.example.cornellnote.api.dto;

import java.util.List;
import java.util.UUID;

public record CreateNoteRequest(
    String title, String cue, String notes, String summary, UUID notebookId, List<UUID> tagIds) {
  public CreateNoteRequest {
    if (tagIds != null) {
      tagIds = List.copyOf(tagIds);
    }
  }
}
