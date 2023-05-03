ALTER TABLE sub_categories
ADD CONSTRAINT fk_sub_category_category
FOREIGN KEY (category_id)
REFERENCES categories(id);