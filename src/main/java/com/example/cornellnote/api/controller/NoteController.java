package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.CreateNoteRequest;
import com.example.cornellnote.api.dto.NoteContentResponse;
import com.example.cornellnote.api.dto.NoteListResponse;
import com.example.cornellnote.api.dto.NoteResponse;
import com.example.cornellnote.api.dto.UpdateNoteRequest;
import com.example.cornellnote.api.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
@SuppressWarnings("EI_EXPOSE_REP2")
public class NoteController {
  private final NoteService noteService;

  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping
  public ResponseEntity<NoteListResponse> listNotes(
      @RequestParam(defaultValue = "20") int limit, @RequestParam(defaultValue = "0") int offset) {
    return ResponseEntity.ok(noteService.listNotes(limit, offset));
  }

  @PostMapping
  public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody CreateNoteRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(request));
  }

  @GetMapping("/{noteId}")
  public ResponseEntity<NoteResponse> getNote(@PathVariable String noteId) {
    return ResponseEntity.ok(noteService.getNote(noteId));
  }

  @PutMapping("/{noteId}")
  public ResponseEntity<NoteResponse> updateNote(
      @PathVariable String noteId, @Valid @RequestBody UpdateNoteRequest request) {
    return ResponseEntity.ok(noteService.updateNote(noteId, request));
  }

  @DeleteMapping("/{noteId}")
  public ResponseEntity<Void> deleteNote(@PathVariable String noteId) {
    noteService.deleteNote(noteId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{noteId}/content")
  public ResponseEntity<NoteContentResponse> getNoteContent(@PathVariable String noteId) {
    return ResponseEntity.ok(noteService.getNoteContent(noteId));
  }
}
