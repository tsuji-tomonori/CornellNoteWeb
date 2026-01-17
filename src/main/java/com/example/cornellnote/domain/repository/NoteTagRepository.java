package com.example.cornellnote.domain.repository;

import com.example.cornellnote.domain.entity.NoteTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteTagRepository extends JpaRepository<NoteTag, NoteTag.NoteTagId> {
  List<NoteTag> findByNoteId(String noteId);
  List<NoteTag> findByTagId(String tagId);
}
