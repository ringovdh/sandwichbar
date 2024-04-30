CREATE TABLE IF NOT EXISTS product_ingredient (
    id              SERIAL PRIMARY KEY,
    product_id      INT NOT NULL REFERENCES product (id),
    ingredient_id   INT NOT NULL REFERENCES ingredient (id)
)