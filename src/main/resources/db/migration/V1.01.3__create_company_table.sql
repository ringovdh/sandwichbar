CREATE TABLE IF NOT EXISTS company (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    branch_id   INT NOT NULL REFERENCES branch (id)
)