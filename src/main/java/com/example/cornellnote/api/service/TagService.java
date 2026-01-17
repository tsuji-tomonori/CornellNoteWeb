package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.TagResponse;
import com.example.cornellnote.domain.entity.Tag;
import com.example.cornellnote.domain.repository.TagRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TagService {
  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public List<TagResponse> listTags(UUID userId) {
    return tagRepository.findByUserIdOrderByNameAsc(userId).stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  private TagResponse toResponse(Tag tag) {
    return new TagResponse(tag.getId(), tag.getName(), tag.getCreatedAt(), tag.getUpdatedAt());
  }
}
