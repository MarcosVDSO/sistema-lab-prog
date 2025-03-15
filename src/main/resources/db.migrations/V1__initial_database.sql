
CREATE TABLE "carts" (
    cart_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    total INTEGER NOT NULL
);

CREATE TABLE "users" (
    user_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cpf VARCHAR(100) NOT NULL UNIQUE,
    cart_id UUID,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,

    CONSTRAINT fk_cart_id
        FOREIGN KEY (cart_id)
        REFERENCES "carts"(cart_id)
        ON DELETE SET NULL
);

CREATE TABLE "addresses" (
    address_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id UUID,
    country VARCHAR(50) NOT NULL,
    "state" VARCHAR(50) NOT NULL,
    landmark VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    cep VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(50) NOT NULL,

    CONSTRAINT fk_user_id
            FOREIGN KEY (user_id)
            REFERENCES "users" (user_id)
            ON DELETE SET NULL
);

CREATE TABLE "categories" (
    category_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL,
    category_description VARCHAR(255) NOT NULL
);

CREATE TABLE "products" (
    product_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    category_id UUID,
    product_name VARCHAR(100) NOT NULL,
    product_description VARCHAR(255) NOT NULL,
    summary VARCHAR(255) NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    brand_name VARCHAR(255) NOT NULL,

    CONSTRAINT fk_category_id
            FOREIGN KEY (category_id)
            REFERENCES "categories"(category_id)
            ON DELETE SET NULL
);


CREATE TABLE "product_skus" (
    product_sku_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    product_id UUID,
    sku VARCHAR(100) NOT NULL,
    stock_quantity INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    product_attributes JSONB,
    product_image VARCHAR(255) NOT NULL,

    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
        REFERENCES "products"(product_id)
        ON DELETE SET NULL
);

CREATE TABLE "cart_items" (
    cart_item_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cart_id UUID,
    product_sku_id UUID,
    quantity INTEGER NOT NULL,

    CONSTRAINT fk_cart_id
        FOREIGN KEY (cart_id)
        REFERENCES "carts"(cart_id)
        ON DELETE SET NULL,

    CONSTRAINT fk_product_sku_id
         FOREIGN KEY (product_sku_id)
         REFERENCES "product_skus"(product_sku_id)
         ON DELETE SET NULL
);

CREATE TABLE "orders" (
    order_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id UUID,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
        REFERENCES "users"(user_id)
        ON DELETE SET NULL
);

CREATE TABLE "order_items" (
    order_item_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    order_id UUID,
    product_sku_id UUID,
    quantity INTEGER NOT NULL,
    price INTEGER NOT NULL,

    CONSTRAINT fk_order_item
        FOREIGN KEY (order_id)
        REFERENCES "orders"(order_id)
        ON DELETE SET NULL,

    CONSTRAINT fk_product_sku_id
        FOREIGN KEY (product_sku_id)
        REFERENCES "product_skus"(product_sku_id)
        ON DELETE SET NULL
);



