package com.example.cornellnote.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notes")
public class Note {
  @Id private UUID id;

  @Column(name = "notebook_id", nullable = false)
  private UUID notebookId;

  @Column(nullable = false)
  private String title;

  @Column(name = "content_ref", nullable = false)
  private String contentRef;

  @Column(name = "summary_ref")
  private String summaryRef;

  @Column(name = "cue_ref")
  private String cueRef;

  @Column(nullable = false)
  private String status;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @Column(name = "deleted_at")
  private Instant deletedAt;

  protected Note() {}

  public Note(
      UUID id,
      UUID notebookId,
      String title,
      String contentRef,
      String summaryRef,
      String cueRef,
      String status,
      Instant createdAt,
      Instant updatedAt,
      Instant deletedAt) {
    this.id = id;
    this.notebookId = notebookId;
    this.title = title;
    this.contentRef = contentRef;
    this.summaryRef = summaryRef;
    this.cueRef = cueRef;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
  }

  public UUID getId() {
    return id;
  }

  public UUID getNotebookId() {
    return notebookId;
  }

  public String getTitle() {
    return title;
  }

  public String getContentRef() {
    return contentRef;
  }

  public String getSummaryRef() {
    return summaryRef;
  }

  public String getCueRef() {
    return cueRef;
  }

  public String getStatus() {
    return status;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public Instant getDeletedAt() {
    return deletedAt;
  }
}
