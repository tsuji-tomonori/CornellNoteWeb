package com.example.cornellnote.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "note_tags")
@IdClass(NoteTag.NoteTagId.class)
public class NoteTag {
  @Id
  @Column(name = "note_id")
  private String noteId;

  @Id
  @Column(name = "tag_id")
  private String tagId;

  protected NoteTag() {
  }

  public NoteTag(String noteId, String tagId) {
    this.noteId = noteId;
    this.tagId = tagId;
  }

  public String getNoteId() {
    return noteId;
  }

  public String getTagId() {
    return tagId;
  }

  public static class NoteTagId implements Serializable {
    private String noteId;
    private String tagId;

    public NoteTagId() {
    }

    public NoteTagId(String noteId, String tagId) {
      this.noteId = noteId;
      this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      NoteTagId that = (NoteTagId) o;
      if (!noteId.equals(that.noteId)) {
        return false;
      }
      return tagId.equals(that.tagId);
    }

    @Override
    public int hashCode() {
      int result = noteId != null ? noteId.hashCode() : 0;
      result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
      return result;
    }
  }
}
