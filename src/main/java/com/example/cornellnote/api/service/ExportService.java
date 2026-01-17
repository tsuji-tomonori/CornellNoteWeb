package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.ExportJobResponse;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ExportService {
  public ExportJobResponse requestExport(String noteId) {
    return new ExportJobResponse(UUID.randomUUID().toString(), noteId, "pending",
        null, Instant.now(), Instant.now());
  }

  public ExportJobResponse getExportStatus(String exportId) {
    return new ExportJobResponse(exportId, "note-001", "completed",
        "http://localhost:3000/mock/export/sample.pdf", Instant.now(), Instant.now());
  }
}
