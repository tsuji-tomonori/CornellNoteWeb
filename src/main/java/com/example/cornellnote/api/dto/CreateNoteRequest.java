package com.example.cornellnote.api.dto;

import java.util.List;

public record CreateNoteRequest(
    String title,
    String cue,
    String notes,
    String summary,
    String notebookId,
    List<String> tagIds
) {
}
