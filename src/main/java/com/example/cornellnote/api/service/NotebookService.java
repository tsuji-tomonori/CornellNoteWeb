package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.NotebookResponse;
import com.example.cornellnote.domain.entity.Notebook;
import com.example.cornellnote.domain.repository.NotebookRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class NotebookService {
  private final NotebookRepository notebookRepository;

  public NotebookService(NotebookRepository notebookRepository) {
    this.notebookRepository = notebookRepository;
  }

  public List<NotebookResponse> listNotebooks(UUID userId) {
    return notebookRepository.findByUserIdOrderBySortOrderAsc(userId).stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  private NotebookResponse toResponse(Notebook notebook) {
    return new NotebookResponse(
        notebook.getId(), notebook.getName(), notebook.getCreatedAt(), notebook.getUpdatedAt());
  }
}
