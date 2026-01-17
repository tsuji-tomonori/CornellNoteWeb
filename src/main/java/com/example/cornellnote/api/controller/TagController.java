package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.TagResponse;
import com.example.cornellnote.api.service.TagService;
import com.example.cornellnote.api.support.SessionUserIdResolver;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagController {
  private final TagService tagService;
  private final SessionUserIdResolver sessionUserIdResolver;

  public TagController(TagService tagService, SessionUserIdResolver sessionUserIdResolver) {
    this.tagService = tagService;
    this.sessionUserIdResolver = sessionUserIdResolver;
  }

  @GetMapping
  public ResponseEntity<List<TagResponse>> listTags(HttpSession session) {
    java.util.UUID userId = sessionUserIdResolver.requireUserId(session);
    return ResponseEntity.ok(tagService.listTags(userId));
  }
}
