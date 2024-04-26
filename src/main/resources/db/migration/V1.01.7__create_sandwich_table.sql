CREATE TABLE IF NOT EXISTS sandwich (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    price       DECIMAL,
    product_id  VARCHAR NOT NULL
)