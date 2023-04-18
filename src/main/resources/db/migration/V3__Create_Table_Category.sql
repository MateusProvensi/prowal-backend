CREATE TABLE IF NOT EXISTS "categories" (
  "id" BIGINT NOT NULL,
  "description" VARCHAR(150) NOT NULL,
  "type" INTEGER NOT NULL,
  "user_id" BIGINT NOT NULL,

  CONSTRAINT "pk_category_id" PRIMARY KEY ("id")
);
