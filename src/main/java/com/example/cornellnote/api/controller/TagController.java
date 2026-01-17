package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.TagResponse;
import com.example.cornellnote.api.service.TagService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagController {
  private final TagService tagService;

  public TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @GetMapping
  public ResponseEntity<List<TagResponse>> listTags() {
    return ResponseEntity.ok(tagService.listTags("user-001"));
  }
}
