package com.example.cornellnote.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "note_tags")
@IdClass(NoteTag.NoteTagId.class)
public class NoteTag {
  @Id
  @Column(name = "note_id")
  private UUID noteId;

  @Id
  @Column(name = "tag_id")
  private UUID tagId;

  protected NoteTag() {}

  public NoteTag(UUID noteId, UUID tagId) {
    this.noteId = noteId;
    this.tagId = tagId;
  }

  public UUID getNoteId() {
    return noteId;
  }

  public UUID getTagId() {
    return tagId;
  }

  public static class NoteTagId implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID noteId;
    private UUID tagId;

    public NoteTagId() {}

    public NoteTagId(UUID noteId, UUID tagId) {
      this.noteId = noteId;
      this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
      boolean matches = this == o;
      if (!matches && o != null && getClass() == o.getClass()) {
        NoteTagId that = (NoteTagId) o;
        if (noteId != null ? !noteId.equals(that.noteId) : that.noteId != null) {
          matches = false;
        } else {
          matches = tagId != null ? tagId.equals(that.tagId) : that.tagId == null;
        }
      }
      return matches;
    }

    @Override
    public int hashCode() {
      int result = noteId != null ? noteId.hashCode() : 0;
      result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
      return result;
    }
  }
}
