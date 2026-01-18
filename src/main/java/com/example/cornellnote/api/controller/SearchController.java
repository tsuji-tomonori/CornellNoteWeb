package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.NoteListResponse;
import com.example.cornellnote.api.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@SuppressWarnings("EI_EXPOSE_REP2")
public class SearchController {
  private final NoteService noteService;

  public SearchController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping
  public ResponseEntity<NoteListResponse> searchNotes(
      @RequestParam(required = false) String q,
      @RequestParam(required = false) String tagId,
      @RequestParam(required = false) String notebookId,
      @RequestParam(defaultValue = "20") int limit,
      @RequestParam(defaultValue = "0") int offset) {
    return ResponseEntity.ok(noteService.listNotes(limit, offset));
  }
}
