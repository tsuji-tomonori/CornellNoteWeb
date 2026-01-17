package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.Note;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, UUID> {
  List<Note> findByNotebookIdOrderByUpdatedAtDesc(UUID notebookId);
}
