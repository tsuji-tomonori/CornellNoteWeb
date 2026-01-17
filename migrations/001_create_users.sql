CREATE TABLE users (
  id uuid PRIMARY KEY,
  email text NOT NULL,
  auth_provider text NOT NULL,
  status text NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now(),
  updated_at timestamptz NOT NULL DEFAULT now(),
  deleted_at timestamptz
);
