CREATE TABLE IF NOT EXISTS "user" (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR,
    userName        VARCHAR,
    email           VARCHAR,
    user_ref        VARCHAR,
    company_id      INT REFERENCES company (id)
)