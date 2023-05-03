CREATE TABLE IF NOT EXISTS "sub_categories" (
  "id" BIGINT NOT NULL,
  "description" VARCHAR(150) NOT NULL,
  "category_id" BIGINT NOT NULL,

  CONSTRAINT "pk_sub_category_id" PRIMARY KEY ("id")
);
