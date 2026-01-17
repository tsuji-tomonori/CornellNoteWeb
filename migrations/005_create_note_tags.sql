CREATE TABLE note_tags (
  note_id uuid NOT NULL REFERENCES notes(id),
  tag_id uuid NOT NULL REFERENCES tags(id),
  PRIMARY KEY (note_id, tag_id)
);

CREATE INDEX note_tags_tag_id_idx ON note_tags(tag_id);
