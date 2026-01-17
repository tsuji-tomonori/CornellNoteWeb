CREATE TABLE notes (
  id uuid PRIMARY KEY,
  notebook_id uuid NOT NULL REFERENCES notebooks(id),
  title text NOT NULL,
  content_ref text NOT NULL,
  summary_ref text,
  cue_ref text,
  status text NOT NULL CHECK (status IN ('draft', 'active', 'archived')),
  created_at timestamptz NOT NULL DEFAULT now(),
  updated_at timestamptz NOT NULL DEFAULT now(),
  deleted_at timestamptz
);

CREATE INDEX notes_notebook_id_idx ON notes(notebook_id);
