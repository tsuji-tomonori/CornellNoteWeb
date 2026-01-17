package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.NotebookResponse;
import com.example.cornellnote.api.error.UnauthorizedException;
import com.example.cornellnote.api.service.NotebookService;
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

  public NotebookController(NotebookService notebookService) {
    this.notebookService = notebookService;
  }

  @GetMapping
  public ResponseEntity<List<NotebookResponse>> listNotebooks(HttpSession session) {
    String userId = session.getAttribute("userId") instanceof String id ? id : null;
    if (userId == null || userId.isBlank()) {
      throw new UnauthorizedException("User session not found");
    }
    return ResponseEntity.ok(notebookService.listNotebooks(userId));
  }
}
