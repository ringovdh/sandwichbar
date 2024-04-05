CREATE TABLE IF NOT EXISTS "user" (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    password    VARCHAR,
    email       VARCHAR,
    company_id  INT REFERENCES company (id)
)