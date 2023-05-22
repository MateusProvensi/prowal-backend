CREATE TABLE IF NOT EXISTS "credit_cards_transactions" (
  "id" BIGINT NOT NULL,
  "description" VARCHAR(150) NOT NULL,
  "date" TIMESTAMPTZ NOT NULL,
  "installment" INTEGER NOT NULL,
  "created_at" TIMESTAMPTZ NOT NULL,
  "updated_at" TIMESTAMPTZ NOT NULL,

  "credit_card_id" BIGINT NOT NULL,

  CONSTRAINT "pk_credit_cards_transactions_id" PRIMARY KEY ("id")
);
