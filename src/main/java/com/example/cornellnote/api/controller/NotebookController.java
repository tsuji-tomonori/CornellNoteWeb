package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.NotebookResponse;
import com.example.cornellnote.api.service.NotebookService;
import com.example.cornellnote.api.support.SessionUserIdResolver;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notebooks")
public class NotebookController {
  private final NotebookService notebookService;
  private final SessionUserIdResolver sessionUserIdResolver;

  public NotebookController(
      NotebookService notebookService, SessionUserIdResolver sessionUserIdResolver) {
    this.notebookService = notebookService;
    this.sessionUserIdResolver = sessionUserIdResolver;
  }

  @GetMapping
  public ResponseEntity<List<NotebookResponse>> listNotebooks(HttpSession session) {
    java.util.UUID userId = sessionUserIdResolver.requireUserId(session);
    return ResponseEntity.ok(notebookService.listNotebooks(userId));
  }
}
