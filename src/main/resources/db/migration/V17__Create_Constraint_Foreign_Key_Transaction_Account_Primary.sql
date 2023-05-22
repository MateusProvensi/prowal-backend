ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_account_primary
FOREIGN KEY (account_primary_id)
REFERENCES accounts(id);