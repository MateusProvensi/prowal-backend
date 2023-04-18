ALTER TABLE categories
ADD CONSTRAINT fk_category_user
FOREIGN KEY (user_id)
REFERENCES users(id);