package com.example.cornellnote.api.dto;

import java.util.List;

public record UpdateNoteRequest(
    String title,
    String cue,
    String notes,
    String summary,
    String notebookId,
    List<String> tagIds
) {
}
