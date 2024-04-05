CREATE TABLE IF NOT EXISTS ingredient (
    id          SERIAL PRIMARY KEY,
    category    VARCHAR,
    name        VARCHAR,
    stock       INT
)