package com.example.cornellnote.api.dto;

import java.util.List;

public record NoteListResponse(List<NoteSummaryResponse> items, int total, int limit, int offset) {
  public NoteListResponse {
    if (items != null) {
      items = List.copyOf(items);
    }
  }
}
