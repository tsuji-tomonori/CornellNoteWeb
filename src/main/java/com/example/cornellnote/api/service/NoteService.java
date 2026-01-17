package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.CreateNoteRequest;
import com.example.cornellnote.api.dto.NoteContentResponse;
import com.example.cornellnote.api.dto.NoteListResponse;
import com.example.cornellnote.api.dto.NoteResponse;
import com.example.cornellnote.api.dto.NoteSummaryResponse;
import com.example.cornellnote.api.dto.TagResponse;
import com.example.cornellnote.api.dto.UpdateNoteRequest;
import com.example.cornellnote.api.error.NotFoundException;
import com.example.cornellnote.domain.entity.Note;
import com.example.cornellnote.domain.entity.NoteTag;
import com.example.cornellnote.domain.entity.Tag;
import com.example.cornellnote.domain.repository.NoteRepository;
import com.example.cornellnote.domain.repository.NoteTagRepository;
import com.example.cornellnote.domain.repository.TagRepository;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteService {
  private final NoteRepository noteRepository;
  private final NoteTagRepository noteTagRepository;
  private final TagRepository tagRepository;

  public NoteService(NoteRepository noteRepository, NoteTagRepository noteTagRepository, TagRepository tagRepository) {
    this.noteRepository = noteRepository;
    this.noteTagRepository = noteTagRepository;
    this.tagRepository = tagRepository;
  }

  public NoteListResponse listNotes(int limit, int offset) {
    List<Note> notes = noteRepository.findAll();
    List<String> noteIds = notes.stream().map(Note::getId).collect(Collectors.toList());
    Map<String, List<TagResponse>> tagsByNote = loadTagsByNote(noteIds);

    List<NoteSummaryResponse> items = notes.stream()
        .sorted((a, b) -> b.getUpdatedAt().compareTo(a.getUpdatedAt()))
        .map(note -> toSummaryResponse(note, tagsByNote.getOrDefault(note.getId(), List.of())))
        .collect(Collectors.toList());

    int total = items.size();
    return new NoteListResponse(items, total, limit, offset);
  }

  public NoteResponse getNote(String noteId) {
    Note note = noteRepository.findById(noteId)
        .orElseThrow(() -> new NotFoundException("Note not found"));
    List<TagResponse> tags = loadTagsByNote(List.of(noteId)).getOrDefault(noteId, List.of());
    return toNoteResponse(note, tags);
  }

  @Transactional
  public NoteResponse createNote(CreateNoteRequest request) {
    String noteId = UUID.randomUUID().toString();
    Instant now = Instant.now();
    Note note = new Note(
        noteId,
        request.notebookId(),
        request.title() == null ? "" : request.title(),
        request.notes() == null ? "" : request.notes(),
        request.summary(),
        request.cue(),
        "active",
        now,
        now,
        null
    );
    noteRepository.save(note);
    applyTags(noteId, request.tagIds());
    return getNote(noteId);
  }

  @Transactional
  public NoteResponse updateNote(String noteId, UpdateNoteRequest request) {
    Note existing = noteRepository.findById(noteId)
        .orElseThrow(() -> new NotFoundException("Note not found"));
    Instant now = Instant.now();

    Note updated = new Note(
        existing.getId(),
        request.notebookId() != null ? request.notebookId() : existing.getNotebookId(),
        request.title() != null ? request.title() : existing.getTitle(),
        request.notes() != null ? request.notes() : existing.getContentRef(),
        request.summary() != null ? request.summary() : existing.getSummaryRef(),
        request.cue() != null ? request.cue() : existing.getCueRef(),
        existing.getStatus(),
        existing.getCreatedAt(),
        now,
        existing.getDeletedAt()
    );
    noteRepository.save(updated);

    if (request.tagIds() != null) {
      applyTags(noteId, request.tagIds());
    }

    return getNote(noteId);
  }

  @Transactional
  public void deleteNote(String noteId) {
    Note existing = noteRepository.findById(noteId)
        .orElseThrow(() -> new NotFoundException("Note not found"));
    Note deleted = new Note(
        existing.getId(),
        existing.getNotebookId(),
        existing.getTitle(),
        existing.getContentRef(),
        existing.getSummaryRef(),
        existing.getCueRef(),
        existing.getStatus(),
        existing.getCreatedAt(),
        Instant.now(),
        Instant.now()
    );
    noteRepository.save(deleted);
  }

  public NoteContentResponse getNoteContent(String noteId) {
    NoteResponse note = getNote(noteId);
    return new NoteContentResponse(note.noteId(), note.cue(), note.notes(), note.summary());
  }

  private Map<String, List<TagResponse>> loadTagsByNote(List<String> noteIds) {
    List<NoteTag> noteTags = noteIds.isEmpty() ? List.of() : noteTagRepository.findAll().stream()
        .filter(noteTag -> noteIds.contains(noteTag.getNoteId()))
        .collect(Collectors.toList());
    List<Tag> tags = tagRepository.findAll();
    Map<String, Tag> tagById = tags.stream().collect(Collectors.toMap(Tag::getId, Function.identity()));

    return noteTags.stream()
        .collect(Collectors.groupingBy(NoteTag::getNoteId,
            Collectors.mapping(noteTag -> tagById.get(noteTag.getTagId()), Collectors.toList())))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            entry -> entry.getValue().stream()
                .filter(tag -> tag != null)
                .map(this::toTagResponse)
                .collect(Collectors.toList())));
  }

  private void applyTags(String noteId, List<String> tagIds) {
    noteTagRepository.findByNoteId(noteId)
        .forEach(noteTagRepository::delete);
    if (tagIds == null || tagIds.isEmpty()) {
      return;
    }
    tagIds.forEach(tagId -> noteTagRepository.save(new NoteTag(noteId, tagId)));
  }

  private NoteSummaryResponse toSummaryResponse(Note note, List<TagResponse> tags) {
    String preview = note.getContentRef();
    if (preview != null && preview.length() > 80) {
      preview = preview.substring(0, 80) + "...";
    }
    return new NoteSummaryResponse(
        note.getId(),
        note.getTitle(),
        note.getNotebookId(),
        tags,
        preview,
        note.getCreatedAt(),
        note.getUpdatedAt()
    );
  }

  private NoteResponse toNoteResponse(Note note, List<TagResponse> tags) {
    return new NoteResponse(
        note.getId(),
        note.getTitle(),
        note.getCueRef(),
        note.getContentRef(),
        note.getSummaryRef(),
        note.getNotebookId(),
        tags,
        note.getCreatedAt(),
        note.getUpdatedAt()
    );
  }

  private TagResponse toTagResponse(Tag tag) {
    return new TagResponse(tag.getId(), tag.getName(), tag.getCreatedAt(), tag.getUpdatedAt());
  }
}
