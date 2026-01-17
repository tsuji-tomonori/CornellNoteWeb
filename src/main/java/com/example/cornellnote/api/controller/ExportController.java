package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.ExportJobResponse;
import com.example.cornellnote.api.service.ExportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExportController {
  private final ExportService exportService;

  public ExportController(ExportService exportService) {
    this.exportService = exportService;
  }

  @PostMapping("/notes/{noteId}/export/pdf")
  public ResponseEntity<ExportJobResponse> requestExport(@PathVariable String noteId) {
    return ResponseEntity.accepted().body(exportService.requestExport(noteId));
  }

  @GetMapping("/exports/{exportId}")
  public ResponseEntity<ExportJobResponse> getExportStatus(@PathVariable String exportId) {
    return ResponseEntity.ok(exportService.getExportStatus(exportId));
  }
}
