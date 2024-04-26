CREATE TABLE IF NOT EXISTS drink (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    price       FLOAT,
    stock       INT,
    product_id   VARCHAR NOT NULL
)