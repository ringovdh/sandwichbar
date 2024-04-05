CREATE TABLE IF NOT EXISTS orderItem (
    id              SERIAL PRIMARY KEY,
    order_id        INT NOT NULL REFERENCES "order" (id),
    price           FLOAT,
    quantity        INT,
    sandwich_id     INT NOT NULL REFERENCES sandwich (id)
)