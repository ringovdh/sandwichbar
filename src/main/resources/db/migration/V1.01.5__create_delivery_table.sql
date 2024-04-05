CREATE TABLE IF NOT EXISTS delivery (
    id              SERIAL PRIMARY KEY,
    status          VARCHAR,
    deliveryDate    DATE,
    address_id      INT NOT NULL REFERENCES address (id)
)