package com.example.cornellnote.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "share_tokens")
public class ShareToken {
  @Id private UUID id;

  @Column(name = "note_id", nullable = false)
  private UUID noteId;

  @Column(name = "token_hash", nullable = false)
  private String tokenHash;

  @Column(nullable = false)
  private String status;

  @Column(name = "expires_at")
  private Instant expiresAt;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "revoked_at")
  private Instant revokedAt;

  protected ShareToken() {}

  public ShareToken(
      UUID id,
      UUID noteId,
      String tokenHash,
      String status,
      Instant expiresAt,
      Instant createdAt,
      Instant revokedAt) {
    this.id = id;
    this.noteId = noteId;
    this.tokenHash = tokenHash;
    this.status = status;
    this.expiresAt = expiresAt;
    this.createdAt = createdAt;
    this.revokedAt = revokedAt;
  }

  public UUID getId() {
    return id;
  }

  public UUID getNoteId() {
    return noteId;
  }

  public String getTokenHash() {
    return tokenHash;
  }

  public String getStatus() {
    return status;
  }

  public Instant getExpiresAt() {
    return expiresAt;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getRevokedAt() {
    return revokedAt;
  }
}
