CREATE TABLE product
(
    product_id      INT IDENTITY(1,1) PRIMARY KEY,
    name            VARCHAR(255),
    product_ref     VARCHAR(255),
    price           DECIMAL,
    product_type    VARCHAR(255) NOT NULL,
    stock           INTEGER
)