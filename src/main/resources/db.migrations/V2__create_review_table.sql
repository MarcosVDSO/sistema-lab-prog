
CREATE TABLE "reviews" (
    review_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    product_id UUID,
    user_id UUID,
    "comment" TEXT NOT NULL,
    "title" VARCHAR(100) NOT NULL,
    "stars" INTEGER NOT NULL,
    "likes" INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
        REFERENCES "products"(product_id)
        ON DELETE SET NULL,

    CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
        REFERENCES "users"(user_id)
        ON DELETE SET NULL
);