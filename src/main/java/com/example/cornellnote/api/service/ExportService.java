package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.ExportJobResponse;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ExportService {
  public ExportJobResponse requestExport(final String noteId) {
    return new ExportJobResponse(
        UUID.randomUUID(), UUID.fromString(noteId), "pending", null, Instant.now(), Instant.now());
  }

  public ExportJobResponse getExportStatus(final String exportId) {
    final UUID exportUuid = parseExportId(exportId);
    return new ExportJobResponse(
        exportUuid,
        UUID.randomUUID(),
        "completed",
        "http://localhost:3000/mock/export/sample.pdf",
        Instant.now(),
        Instant.now());
  }

  private UUID parseExportId(final String exportId) {
    UUID result = UUID.randomUUID();
    if (exportId != null && !exportId.isBlank()) {
      try {
        result = UUID.fromString(exportId);
      } catch (IllegalArgumentException ex) {
        result = UUID.nameUUIDFromBytes(exportId.getBytes(java.nio.charset.StandardCharsets.UTF_8));
      }
    }
    return result;
  }
}
