package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.E2eSeedResponse;
import com.example.cornellnote.domain.entity.Note;
import com.example.cornellnote.domain.entity.NoteTag;
import com.example.cornellnote.domain.entity.Notebook;
import com.example.cornellnote.domain.entity.Tag;
import com.example.cornellnote.domain.entity.UserAccount;
import com.example.cornellnote.domain.repository.NoteRepository;
import com.example.cornellnote.domain.repository.NoteTagRepository;
import com.example.cornellnote.domain.repository.NotebookRepository;
import com.example.cornellnote.domain.repository.TagRepository;
import com.example.cornellnote.domain.repository.UserAccountRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/e2e")
public class E2eSeedController {
  private static final UUID USER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
  private static final UUID NOTEBOOK_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");
  private static final UUID SEED_NOTE_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");
  private static final UUID SECONDARY_NOTE_ID =
      UUID.fromString("44444444-4444-4444-4444-444444444444");
  private static final UUID TAG_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");

  private final UserAccountRepository userAccountRepository;
  private final NotebookRepository notebookRepository;
  private final TagRepository tagRepository;
  private final NoteRepository noteRepository;
  private final NoteTagRepository noteTagRepository;

  public E2eSeedController(
      UserAccountRepository userAccountRepository,
      NotebookRepository notebookRepository,
      TagRepository tagRepository,
      NoteRepository noteRepository,
      NoteTagRepository noteTagRepository) {
    this.userAccountRepository = userAccountRepository;
    this.notebookRepository = notebookRepository;
    this.tagRepository = tagRepository;
    this.noteRepository = noteRepository;
    this.noteTagRepository = noteTagRepository;
  }

  @PostMapping("/seed")
  @Transactional
  public ResponseEntity<E2eSeedResponse> seed() {
    noteTagRepository.deleteAll(noteTagRepository.findByNoteId(SEED_NOTE_ID));
    noteTagRepository.deleteAll(noteTagRepository.findByNoteId(SECONDARY_NOTE_ID));

    noteRepository.findById(SEED_NOTE_ID).ifPresent(noteRepository::delete);
    noteRepository.findById(SECONDARY_NOTE_ID).ifPresent(noteRepository::delete);
    tagRepository.findById(TAG_ID).ifPresent(tagRepository::delete);
    notebookRepository.findById(NOTEBOOK_ID).ifPresent(notebookRepository::delete);
    userAccountRepository.findById(USER_ID).ifPresent(userAccountRepository::delete);

    Instant now = Instant.now();
    userAccountRepository.save(
        new UserAccount(USER_ID, "demo@example.com", "local", "active", now, now, null));
    notebookRepository.save(new Notebook(NOTEBOOK_ID, USER_ID, "プログラミング学習", 0, now, now, null));
    tagRepository.save(new Tag(TAG_ID, USER_ID, "JavaScript", now, now));

    noteRepository.save(
        new Note(
            SEED_NOTE_ID,
            NOTEBOOK_ID,
            "JavaScript Async",
            "JavaScript async/await のメモ",
            "非同期処理の要点",
            "async/await",
            "active",
            now,
            now,
            null));
    noteRepository.save(
        new Note(
            SECONDARY_NOTE_ID,
            NOTEBOOK_ID,
            "React Hooksの使い方",
            "React Hooks を使った状態管理",
            "Hooksの概要",
            "useState/useEffect",
            "active",
            now,
            now,
            null));

    noteTagRepository.save(new NoteTag(SEED_NOTE_ID, TAG_ID));

    return ResponseEntity.ok(
        new E2eSeedResponse(USER_ID, NOTEBOOK_ID, SEED_NOTE_ID, SECONDARY_NOTE_ID, TAG_ID));
  }
}
