ALTER TABLE Categories DROP CONSTRAINT fk_product_id;
ALTER TABLE Categories DROP COLUMN product_id;
ALTER TABLE products ADD category_id UUID;
ALTER TABLE Products
    ADD CONSTRAINT fk_category_id
        FOREIGN KEY (category_id)
            REFERENCES Categories(category_id)
            ON DELETE SET NULL;
