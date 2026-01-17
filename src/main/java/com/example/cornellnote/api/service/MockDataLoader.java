package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.NoteResponse;
import com.example.cornellnote.api.dto.NoteSummaryResponse;
import com.example.cornellnote.api.dto.NotebookResponse;
import com.example.cornellnote.api.dto.PublicNoteResponse;
import com.example.cornellnote.api.dto.TagResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class MockDataLoader {
  private final ObjectMapper objectMapper;

  public MockDataLoader(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public List<NotebookResponse> loadNotebooks() {
    Map<String, Object> payload = readJson("mock/api/notebooks.json");
    return objectMapper.convertValue(payload.get("notebooks"), objectMapper.getTypeFactory()
        .constructCollectionType(List.class, NotebookResponse.class));
  }

  public List<TagResponse> loadTags() {
    Map<String, Object> payload = readJson("mock/api/tags.json");
    return objectMapper.convertValue(payload.get("tags"), objectMapper.getTypeFactory()
        .constructCollectionType(List.class, TagResponse.class));
  }

  public List<NoteSummaryResponse> loadNoteSummaries() {
    Map<String, Object> payload = readJson("mock/api/notes.json");
    return objectMapper.convertValue(payload.get("notes"), objectMapper.getTypeFactory()
        .constructCollectionType(List.class, NoteSummaryResponse.class));
  }

  public NoteResponse loadNote(String noteId) {
    Map<String, Object> payload = readJson("mock/api/note-" + noteId + ".json");
    NoteResponse note = objectMapper.convertValue(payload, NoteResponse.class);
    return new NoteResponse(note.noteId(), note.title(), note.cue(), note.notes(), note.summary(),
        note.notebookId(), note.tags(), note.createdAt(), note.updatedAt());
  }

  public PublicNoteResponse loadSharedNote(String token) {
    Map<String, Object> payload = readJson("mock/api/share-" + token + ".json");
    Map<String, Object> note = (Map<String, Object>) payload.get("note");
    return objectMapper.convertValue(note, PublicNoteResponse.class);
  }

  private Map<String, Object> readJson(String path) {
    try (InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {
      if (input == null) {
        throw new IllegalArgumentException("Mock data not found: " + path);
      }
      return objectMapper.readValue(input, Map.class);
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to read mock data: " + path, ex);
    }
  }
}
