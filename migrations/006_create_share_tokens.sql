CREATE TABLE share_tokens (
  id uuid PRIMARY KEY,
  note_id uuid NOT NULL REFERENCES notes(id),
  token_hash text NOT NULL,
  status text NOT NULL CHECK (status IN ('active', 'revoked', 'expired')),
  expires_at timestamptz,
  created_at timestamptz NOT NULL DEFAULT now(),
  revoked_at timestamptz
);

CREATE INDEX share_tokens_note_id_idx ON share_tokens(note_id);
