package com.example.cornellnote.api.dto;

public record PublicNoteResponse(
    java.util.UUID noteId,
    String title,
    String cue,
    String notes,
    String summary,
    java.time.Instant createdAt) {}
