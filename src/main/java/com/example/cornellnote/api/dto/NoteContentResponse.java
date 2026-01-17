package com.example.cornellnote.api.dto;

public record NoteContentResponse(
    String noteId,
    String cue,
    String notes,
    String summary
) {
}
