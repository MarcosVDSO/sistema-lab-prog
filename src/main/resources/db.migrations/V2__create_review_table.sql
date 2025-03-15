
CREATE TABLE "reviews" (
    review_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    product_id UUID,
    user_id UUID,
    "comment" TEXT NOT NULL,
    "stars" INTEGER NOT NULL,

    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
        REFERENCES "products"(product_id)
        ON DELETE SET NULL,

    CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
        REFERENCES "users"(user_id)
        ON DELETE SET NULL
);