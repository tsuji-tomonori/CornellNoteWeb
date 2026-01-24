package com.example.cornellnote.api.dto;

import java.time.Instant;
import java.util.UUID;

public record ShareLinkResponse(UUID shareToken, String url, UUID noteId, Instant createdAt) {}
