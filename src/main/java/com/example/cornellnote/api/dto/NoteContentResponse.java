package com.example.cornellnote.api.dto;

import java.util.UUID;

public record NoteContentResponse(UUID noteId, String cue, String notes, String summary) {}
