package com.example.cornellnote.api.dto;

import java.time.Instant;

public record ShareLinkResponse(
    String shareToken,
    String url,
    String noteId,
    Instant createdAt
) {
}
