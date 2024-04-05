CREATE TABLE IF NOT EXISTS address (
    id          SERIAL PRIMARY KEY,
    city        VARCHAR,
    street      VARCHAR,
    postcode    INT,
    housenumber VARCHAR
)