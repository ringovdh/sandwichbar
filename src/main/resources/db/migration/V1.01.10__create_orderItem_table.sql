CREATE TABLE IF NOT EXISTS orderItem (
    id              SERIAL PRIMARY KEY,
    order_id        INT NOT NULL REFERENCES "order" (id),
    quantity        INT,
    product_id     INT REFERENCES product (id)
)