package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.NoteTag;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteTagRepository extends JpaRepository<NoteTag, NoteTag.NoteTagId> {
  List<NoteTag> findByNoteId(UUID noteId);

  List<NoteTag> findByTagId(UUID tagId);
}
