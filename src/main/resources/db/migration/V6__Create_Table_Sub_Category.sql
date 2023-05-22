CREATE TABLE IF NOT EXISTS "sub_categories" (
  "id" BIGINT NOT NULL,
  "description" VARCHAR(150) NOT NULL,
  "category_id" BIGINT NOT NULL,
  "enabled" BOOLEAN NOT NULL,
  "created_at" TIMESTAMPTZ NOT NULL,
  "updated_at" TIMESTAMPTZ NOT NULL,

  CONSTRAINT "pk_sub_category_id" PRIMARY KEY ("id")
);
