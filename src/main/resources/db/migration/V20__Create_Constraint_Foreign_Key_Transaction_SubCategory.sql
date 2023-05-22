ALTER TABLE transactions
ADD CONSTRAINT fk_transactions_subcategory
FOREIGN KEY (subcategory_id)
REFERENCES sub_categories(id);