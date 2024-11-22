
CREATE TABLE Addresses (
    addressId UUID PRIMARY KEY,
    country VARCHAR(50) NOT NULL,
    "state" VARCHAR(50) NOT NULL,
    landmark VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    cep VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(50) NOT NULL,
    createdAt TIMESTAMPTZ NOT NULL
);

CREATE TABLE Employees (
    employeeId UUID PRIMARY KEY,
    addressId UUID,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profilePhoto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,

    CONSTRAINT fk_addressId 
        FOREIGN KEY (addressId)
        REFERENCES Addresses(addressId)
        ON DELETE SET NULL
);

CREATE TABLE Admins (
    adminId UUID PRIMARY KEY,
    addressId UUID,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profilePhoto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,

    CONSTRAINT fk_addressId 
        FOREIGN KEY (addressId)
        REFERENCES Addresses(addressId)
        ON DELETE SET NULL
);

CREATE TABLE Carts (
    cartId UUID PRIMARY KEY,
    total INTEGER NOT NULL,
    createdAt TIMESTAMPTZ NOT NULL,
    updatedAt TIMESTAMPTZ NOT NULL
);

CREATE TABLE Customers (
    customerId UUID PRIMARY KEY,
    addressId UUID,
    cartId UUID,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    "password" VARCHAR(100) NOT NULL,
    profilePhoto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,

    CONSTRAINT fk_addressId 
        FOREIGN KEY (addressId)
        REFERENCES Addresses(addressId)
        ON DELETE SET NULL,

    CONSTRAINT fk_cartId 
        FOREIGN KEY (cartId)
        REFERENCES Carts(cartId)
        ON DELETE SET NULL
);

CREATE TABLE CartItems (
    cartItemId UUID PRIMARY KEY,
    cartId UUID,
    quantity INTEGER NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,

    CONSTRAINT fk_cartId 
        FOREIGN KEY (cartId)
        REFERENCES Carts(cartId)
        ON DELETE SET NULL
);

CREATE TABLE ProductSkus (
    productSkuId UUID PRIMARY KEY,
    cartItemId UUID,
    sku VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,

    CONSTRAINT fk_cartItemId 
        FOREIGN KEY (cartItemId)
        REFERENCES CartItems(cartItemId)
        ON DELETE SET NULL
);

CREATE TABLE Products (
    productId UUID PRIMARY KEY,
    productSkuId UUID,
    productName VARCHAR(100) NOT NULL,
    productDescription VARCHAR(255) NOT NULL,
    summary VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,

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





