CREATE SCHEMA IF NOT EXISTS TEST;
SET SCHEMA TEST;

CREATE TABLE users (
  id uuid PRIMARY KEY,
  email text NOT NULL,
  auth_provider text NOT NULL,
  status text NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  deleted_at timestamp
);

CREATE TABLE notebooks (
  id uuid PRIMARY KEY,
  user_id uuid NOT NULL,
  name text NOT NULL,
  sort_order integer NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  deleted_at timestamp
);

CREATE TABLE notes (
  id uuid PRIMARY KEY,
  notebook_id uuid NOT NULL,
  title text NOT NULL,
  content_ref text NOT NULL,
  summary_ref text,
  cue_ref text,
  status text NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,
  deleted_at timestamp
);

CREATE TABLE tags (
  id uuid PRIMARY KEY,
  user_id uuid NOT NULL,
  name text NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL
);

CREATE TABLE note_tags (
  note_id uuid NOT NULL,
  tag_id uuid NOT NULL,
  PRIMARY KEY (note_id, tag_id)
);

CREATE TABLE share_tokens (
  id uuid PRIMARY KEY,
  note_id uuid NOT NULL,
  token_hash text NOT NULL,
  status text NOT NULL,
  expires_at timestamp,
  created_at timestamp NOT NULL,
  revoked_at timestamp
);

CREATE TABLE export_jobs (
  id uuid PRIMARY KEY,
  note_id uuid NOT NULL,
  status text NOT NULL,
  output_ref text,
  error_message text,
  created_at timestamp NOT NULL,
  completed_at timestamp
);
