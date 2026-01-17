package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.ExportJobResponse;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ExportService {
  public ExportJobResponse requestExport(String noteId) {
    return new ExportJobResponse(UUID.randomUUID(), UUID.fromString(noteId), "pending",
        null, Instant.now(), Instant.now());
  }

  public ExportJobResponse getExportStatus(String exportId) {
    UUID exportUuid = UUID.fromString(exportId);
    return new ExportJobResponse(exportUuid, UUID.randomUUID(), "completed",
        "http://localhost:3000/mock/export/sample.pdf", Instant.now(), Instant.now());
  }
}
