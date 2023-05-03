ALTER TABLE accounts
ADD CONSTRAINT fk_account_user
FOREIGN KEY (user_id)
REFERENCES users(id);