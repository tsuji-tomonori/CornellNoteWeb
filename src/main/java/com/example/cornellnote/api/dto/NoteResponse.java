package com.example.cornellnote.api.dto;

import java.time.Instant;
import java.util.List;

public record NoteResponse(
    String noteId,
    String title,
    String cue,
    String notes,
    String summary,
    String notebookId,
    List<TagResponse> tags,
    Instant createdAt,
    Instant updatedAt
) {
}
