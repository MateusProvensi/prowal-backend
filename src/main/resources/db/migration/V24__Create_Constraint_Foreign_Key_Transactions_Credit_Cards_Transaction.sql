ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_credit_card_transaction
FOREIGN KEY (credit_card_transaction_id)
REFERENCES credit_cards_transactions(id);
