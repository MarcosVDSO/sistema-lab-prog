
CREATE TABLE Employees (
    employeeId UUID PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profilePhoto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Admins (
    adminId UUID PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profilePhoto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Carts (
    cartId UUID PRIMARY KEY,
    total INTEGER NOT NULL,
    createdAt TIMESTAMPTZ NOT NULL,
    updatedAt TIMESTAMPTZ NOT NULL
);

CREATE TABLE Customers (
    customerId UUID PRIMARY KEY,
    cartId UUID,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profilePhoto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,

    CONSTRAINT fk_cartId 
        FOREIGN KEY (cartId)
        REFERENCES Carts(cartId)
        ON DELETE SET NULL
);

CREATE TABLE Addresses (
    addressId UUID PRIMARY KEY,
    adminId UUID,
    employeeId UUID,
    customerId UUID,
    country VARCHAR(50) NOT NULL,
    "state" VARCHAR(50) NOT NULL,
    landmark VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    cep VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(50) NOT NULL,
    createdAt TIMESTAMPTZ NOT NULL,

    CONSTRAINT fk_adminId
        FOREIGN KEY (adminId)
        REFERENCES Admins(adminId)
        ON DELETE SET NULL,

    CONSTRAINT fk_employeeId
            FOREIGN KEY (employeeId)
            REFERENCES Employees(employeeId)
            ON DELETE SET NULL,

    CONSTRAINT fk_customerId
            FOREIGN KEY (customerId)
            REFERENCES Customers(customerId)
            ON DELETE SET NULL
);

CREATE TABLE Products (
    productId UUID PRIMARY KEY,
    productName VARCHAR(100) NOT NULL,
    productDescription VARCHAR(255) NOT NULL,
    summary VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL
);


CREATE TABLE ProductSkus (
    productSkuId UUID PRIMARY KEY,
    productId UUID,
    sku VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,

    CONSTRAINT fk_productId
        FOREIGN KEY (productId)
        REFERENCES Products(productId)
        ON DELETE SET NULL
);

CREATE TABLE CartItems (
    cartItemId UUID PRIMARY KEY,
    cartId UUID,
    productSkuId UUID,
    quantity INTEGER NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,

    CONSTRAINT fk_cartId 
        FOREIGN KEY (cartId)
        REFERENCES Carts(cartId)
        ON DELETE SET NULL,

    CONSTRAINT fk_productSkuId
         FOREIGN KEY (productSkuId)
         REFERENCES ProductSkus(productSkuId)
         ON DELETE SET NULL
);

CREATE TABLE Categories (
    categoryId UUID PRIMARY KEY,
    productId UUID,
    categoryName VARCHAR(255) NOT NULL,
    categoryDescription VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,

    CONSTRAINT fk_productId 
        FOREIGN KEY (productId)
        REFERENCES Products(productId)
        ON DELETE SET NULL
);

CREATE TABLE SubCategories (
    subCategoryId UUID PRIMARY KEY,
    categoryId UUID,
    subCategoryName VARCHAR(255) NOT NULL,
    subCategoryDescription VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,

    CONSTRAINT fk_categoryId 
        FOREIGN KEY (categoryId)
        REFERENCES Categories(categoryId)
        ON DELETE SET NULL
);





