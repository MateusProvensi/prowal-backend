ALTER TABLE credit_cards_transactions
ADD CONSTRAINT fk_credit_cards_transactions_credit_card
FOREIGN KEY (credit_card_id)
REFERENCES credit_cards(id);