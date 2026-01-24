package com.example.cornellnote.api.dto;

import java.time.Instant;
import java.util.UUID;

public record NotebookResponse(
    UUID notebookId, String name, Instant createdAt, Instant updatedAt) {}
