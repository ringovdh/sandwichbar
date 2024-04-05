CREATE TABLE IF NOT EXISTS drink (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    price       FLOAT,
    stock       INT
)