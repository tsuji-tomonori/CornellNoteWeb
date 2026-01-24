package com.example.cornellnote.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "export_jobs")
public class ExportJob {
  @Id private UUID id;

  @Column(name = "note_id", nullable = false)
  private UUID noteId;

  @Column(nullable = false)
  private String status;

  @Column(name = "output_ref")
  private String outputRef;

  @Column(name = "error_message")
  private String errorMessage;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "completed_at")
  private Instant completedAt;

  protected ExportJob() {}

  public ExportJob(
      UUID id,
      UUID noteId,
      String status,
      String outputRef,
      String errorMessage,
      Instant createdAt,
      Instant completedAt) {
    this.id = id;
    this.noteId = noteId;
    this.status = status;
    this.outputRef = outputRef;
    this.errorMessage = errorMessage;
    this.createdAt = createdAt;
    this.completedAt = completedAt;
  }

  public UUID getId() {
    return id;
  }

  public UUID getNoteId() {
    return noteId;
  }

  public String getStatus() {
    return status;
  }

  public String getOutputRef() {
    return outputRef;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getCompletedAt() {
    return completedAt;
  }
}
