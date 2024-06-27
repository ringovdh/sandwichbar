CREATE TABLE IF NOT EXISTS "user" (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR,
    userName        VARCHAR,
    email           VARCHAR,
    user_ref        VARCHAR,
    address_id      INT REFERENCES address (id)
)