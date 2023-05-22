CREATE TABLE IF NOT EXISTS "users" (
  "id" BIGINT NOT NULL,
  "first_name" VARCHAR(150) NOT NULL,
  "last_name" VARCHAR(150) NOT NULL,
  "user_name" VARCHAR(100) NOT NULL,
  "password" VARCHAR NOT NULL,
  "account_non_expired" BOOLEAN DEFAULT true,
  "account_non_locked" BOOLEAN DEFAULT true,
  "credentials_non_expired" BOOLEAN DEFAULT true,
  "enabled" BOOLEAN DEFAULT true,
  "created_at" TIMESTAMPTZ NOT NULL,
  "updated_at" TIMESTAMPTZ NOT NULL,

  CONSTRAINT "pk_user_id" PRIMARY KEY ("id")
);
