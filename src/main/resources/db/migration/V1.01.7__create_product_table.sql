CREATE TABLE IF NOT EXISTS product (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR,
    price           DECIMAL,
    product_type    VARCHAR NOT NULL,
    stock           INTEGER
)