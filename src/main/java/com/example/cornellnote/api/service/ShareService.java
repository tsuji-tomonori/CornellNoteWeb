package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.PublicNoteResponse;
import com.example.cornellnote.api.dto.ShareLinkResponse;
import com.example.cornellnote.api.error.BadRequestException;
import com.example.cornellnote.api.error.NotFoundException;
import com.example.cornellnote.domain.entity.Note;
import com.example.cornellnote.domain.entity.ShareToken;
import com.example.cornellnote.domain.repository.NoteRepository;
import com.example.cornellnote.domain.repository.ShareTokenRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShareService {
  private final NoteRepository noteRepository;
  private final ShareTokenRepository shareTokenRepository;

  public ShareService(NoteRepository noteRepository, ShareTokenRepository shareTokenRepository) {
    this.noteRepository = noteRepository;
    this.shareTokenRepository = shareTokenRepository;
  }

  public ShareLinkResponse createShareLink(String noteId) {
    UUID noteUuid = parseUuid(noteId, "noteId");
    Note note =
        noteRepository
            .findById(noteUuid)
            .orElseThrow(() -> new NotFoundException("Note not found"));
    UUID shareToken = UUID.randomUUID();
    Instant now = Instant.now();
    ShareToken record =
        new ShareToken(
            UUID.randomUUID(), note.getId(), shareToken.toString(), "active", null, now, null);
    shareTokenRepository.save(record);
    String url = "http://localhost:3000/share.html?token=" + shareToken;
    return new ShareLinkResponse(shareToken, url, note.getId(), now);
  }

  public PublicNoteResponse getSharedNote(String token) {
    ShareToken record =
        shareTokenRepository
            .findByTokenHash(token)
            .orElseThrow(() -> new NotFoundException("Shared note not found"));
    final String activeStatus = "active";
    if (!activeStatus.equals(record.getStatus()) || isExpired(record.getExpiresAt())) {
      throw new NotFoundException("Shared note not found");
    }
    Note note =
        noteRepository
            .findById(record.getNoteId())
            .orElseThrow(() -> new NotFoundException("Shared note not found"));
    return new PublicNoteResponse(
        note.getId(),
        safeText(note.getTitle()),
        safeText(note.getCueRef()),
        safeText(note.getContentRef()),
        safeText(note.getSummaryRef()),
        note.getCreatedAt());
  }

  public void revokeShareLink(String token) {
    ShareToken record =
        shareTokenRepository
            .findByTokenHash(token)
            .orElseThrow(() -> new NotFoundException("Share link not found"));
    final String revokedStatus = "revoked";
    if (revokedStatus.equals(record.getStatus())) {
      return;
    }
    ShareToken revoked =
        new ShareToken(
            record.getId(),
            record.getNoteId(),
            record.getTokenHash(),
            "revoked",
            record.getExpiresAt(),
            record.getCreatedAt(),
            Instant.now());
    shareTokenRepository.save(revoked);
  }

  private boolean isExpired(Instant expiresAt) {
    return expiresAt != null && expiresAt.isBefore(Instant.now());
  }

  private String safeText(String value) {
    return value == null ? "" : value;
  }

  private UUID parseUuid(String value, String fieldName) {
    if (value == null || value.isBlank()) {
      throw new BadRequestException(fieldName + "が不正です");
    }
    try {
      return UUID.fromString(value);
    } catch (IllegalArgumentException ex) {
      throw new BadRequestException(fieldName + "が不正です", ex);
    }
  }
}
