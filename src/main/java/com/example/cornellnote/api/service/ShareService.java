package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.PublicNoteResponse;
import com.example.cornellnote.api.dto.ShareLinkResponse;
import com.example.cornellnote.api.error.NotFoundException;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShareService {
  private final MockDataLoader mockData;

  public ShareService(MockDataLoader mockData) {
    this.mockData = mockData;
  }

  public ShareLinkResponse createShareLink(String noteId) {
    return new ShareLinkResponse(UUID.randomUUID().toString(),
        "http://localhost:3000/share/abc123",
        noteId,
        Instant.now());
  }

  public PublicNoteResponse getSharedNote(String token) {
    try {
      return mockData.loadSharedNote(token);
    } catch (IllegalArgumentException ex) {
      throw new NotFoundException("Shared note not found");
    }
  }

  public void revokeShareLink(String token) {
  }
}
