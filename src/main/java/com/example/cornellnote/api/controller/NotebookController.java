package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.NotebookResponse;
import com.example.cornellnote.api.service.NotebookService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notebooks")
public class NotebookController {
  private final NotebookService notebookService;

  public NotebookController(NotebookService notebookService) {
    this.notebookService = notebookService;
  }

  @GetMapping
  public ResponseEntity<List<NotebookResponse>> listNotebooks() {
    return ResponseEntity.ok(notebookService.listNotebooks("user-001"));
  }
}
