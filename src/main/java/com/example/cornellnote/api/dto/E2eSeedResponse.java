package com.example.cornellnote.api.dto;

import java.util.UUID;

public record E2eSeedResponse(
    UUID userId, UUID notebookId, UUID seedNoteId, UUID secondaryNoteId, UUID tagId) {}
