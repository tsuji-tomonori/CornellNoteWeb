package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.PublicNoteResponse;
import com.example.cornellnote.api.dto.ShareLinkResponse;
import com.example.cornellnote.api.service.ShareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShareController {
  private final ShareService shareService;

  public ShareController(ShareService shareService) {
    this.shareService = shareService;
  }

  @PostMapping("/notes/{noteId}/share")
  public ResponseEntity<ShareLinkResponse> createShareLink(@PathVariable String noteId) {
    return ResponseEntity.status(HttpStatus.CREATED).body(shareService.createShareLink(noteId));
  }

  @GetMapping("/share/{shareToken}")
  public ResponseEntity<PublicNoteResponse> getSharedNote(@PathVariable String shareToken) {
    return ResponseEntity.ok(shareService.getSharedNote(shareToken));
  }

  @DeleteMapping("/share/{shareToken}")
  public ResponseEntity<Void> revokeShare(@PathVariable String shareToken) {
    shareService.revokeShareLink(shareToken);
    return ResponseEntity.noContent().build();
  }
}
