CREATE TABLE IF NOT EXISTS "accounts" (
  "id" BIGINT NOT NULL,
  "description" VARCHAR(150) NOT NULL,
  "user_id" BIGINT NOT NULL,

  CONSTRAINT "pk_account_id" PRIMARY KEY ("id")
);
