CREATE TABLE IF NOT EXISTS branch (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    address_id  INT NOT NULL REFERENCES address (id)
)