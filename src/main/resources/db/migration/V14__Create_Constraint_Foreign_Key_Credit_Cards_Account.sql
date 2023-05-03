ALTER TABLE credit_cards
ADD CONSTRAINT fk_credit_cards_account
FOREIGN KEY (account_id)
REFERENCES accounts(id);