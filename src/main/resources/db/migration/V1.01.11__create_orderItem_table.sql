CREATE TABLE IF NOT EXISTS orderItem (
    id              SERIAL PRIMARY KEY,
    order_id        INT NOT NULL REFERENCES "order" (id),
    price           FLOAT,
    quantity        INT,
    sandwich_id     INT REFERENCES sandwich (id),
    drink_id        INT REFERENCES drink (id)
)