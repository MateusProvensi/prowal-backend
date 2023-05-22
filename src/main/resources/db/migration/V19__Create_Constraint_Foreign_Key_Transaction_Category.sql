ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_category
FOREIGN KEY (category_id)
REFERENCES categories(id);