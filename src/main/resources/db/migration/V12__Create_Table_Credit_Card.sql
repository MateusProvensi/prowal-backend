CREATE TABLE IF NOT EXISTS "credit_cards" (
  "id" BIGINT NOT NULL,
  "description" VARCHAR(150) NOT NULL,
  "final_numbers" VARCHAR(4),
  "limit_value" NUMERIC(10, 2)  NOT NULL,
  "invoice_date" VARCHAR(2)  NOT NULL,
  "due_date" VARCHAR(2) NOT NULL,

  "account_id" BIGINT NOT NULL,

  CONSTRAINT "pk_credit_card_id" PRIMARY KEY ("id")
);
