
CREATE TABLE Employees (
    employee_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profile_photo VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Admins (
    admin_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profile_photo VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Carts (
    cart_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    total INTEGER NOT NULL,
    createdAt TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Customers (
    customer_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cart_id UUID,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profile_photo VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,

    CONSTRAINT fk_cart_id
        FOREIGN KEY (cart_id)
        REFERENCES Carts(cart_id)
        ON DELETE SET NULL
);

CREATE TABLE Addresses (
    address_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    admin_id UUID,
    employee_id UUID,
    customer_id UUID,
    country VARCHAR(50) NOT NULL,
    "state" VARCHAR(50) NOT NULL,
    landmark VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    cep VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(50) NOT NULL,
    createdAt TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_admin_id
        FOREIGN KEY (admin_id)
        REFERENCES Admins(admin_id)
        ON DELETE SET NULL,

    CONSTRAINT fk_employee_id
            FOREIGN KEY (employee_id)
            REFERENCES Employees(employee_id)
            ON DELETE SET NULL,

    CONSTRAINT fk_customer_id
            FOREIGN KEY (customer_id)
            REFERENCES Customers(customer_id)
            ON DELETE SET NULL
);

CREATE TABLE Products (
    product_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    product_description VARCHAR(255) NOT NULL,
    summary VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE ProductSkus (
    product_sku_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    product_id UUID,
    sku VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
        REFERENCES Products(product_id)
        ON DELETE SET NULL
);

CREATE TABLE CartItems (
    cart_item_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cart_id UUID,
    product_sku_id UUID,
    quantity INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cart_id
        FOREIGN KEY (cart_id)
        REFERENCES Carts(cart_id)
        ON DELETE SET NULL,

    CONSTRAINT fk_product_sku_id
         FOREIGN KEY (product_sku_id)
         REFERENCES ProductSkus(product_sku_id)
         ON DELETE SET NULL
);

CREATE TABLE Categories (
    category_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    product_id UUID,
    category_name VARCHAR(255) NOT NULL,
    category_description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
        REFERENCES Products(product_id)
        ON DELETE SET NULL
);

CREATE TABLE SubCategories (
    subCategory_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    category_id UUID,
    subCategory_name VARCHAR(255) NOT NULL,
    subCategory_description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_category_id
        FOREIGN KEY (category_id)
        REFERENCES Categories(category_id)
        ON DELETE SET NULL
);





