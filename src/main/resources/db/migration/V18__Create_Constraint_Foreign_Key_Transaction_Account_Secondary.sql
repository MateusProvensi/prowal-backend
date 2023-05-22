ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_account_secondary
FOREIGN KEY (account_secondary_id)
REFERENCES accounts(id);