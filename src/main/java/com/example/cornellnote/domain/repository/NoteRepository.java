package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.Note;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, String> {
  List<Note> findByNotebookIdOrderByUpdatedAtDesc(String notebookId);
}
