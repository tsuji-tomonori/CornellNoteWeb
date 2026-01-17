CREATE TABLE export_jobs (
  id uuid PRIMARY KEY,
  note_id uuid NOT NULL REFERENCES notes(id),
  status text NOT NULL CHECK (status IN ('queued', 'running', 'succeeded', 'failed')),
  output_ref text,
  error_message text,
  created_at timestamptz NOT NULL DEFAULT now(),
  completed_at timestamptz
);

CREATE INDEX export_jobs_note_id_idx ON export_jobs(note_id);
