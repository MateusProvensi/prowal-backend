CREATE TABLE IF NOT EXISTS "transactions" (
  "id" BIGINT NOT NULL,
  "description" VARCHAR(150) NOT NULL,
  "type" INTEGER NOT NULL,
  "date" TIMESTAMPTZ NOT NULL,
  "value" NUMERIC(10, 2) NOT NULL,
  "created_at" TIMESTAMPTZ NOT NULL,
  "updated_at" TIMESTAMPTZ NOT NULL,

  "account_primary_id" BIGINT NOT NULL,
  "account_secondary_id" BIGINT,
  "category_id" BIGINT,
  "credit_card_transaction_id" BIGINT,
  "subcategory_id" BIGINT,

  CONSTRAINT "pk_transactions_id" PRIMARY KEY ("id")
);
