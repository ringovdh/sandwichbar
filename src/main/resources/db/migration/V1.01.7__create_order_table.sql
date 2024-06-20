CREATE TABLE IF NOT EXISTS "order" (
    id              SERIAL PRIMARY KEY,
    user_id         INT NOT NULL REFERENCES "user" (id)
)