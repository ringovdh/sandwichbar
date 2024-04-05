CREATE TABLE IF NOT EXISTS sandwich_ingredient (
    id              SERIAL PRIMARY KEY,
    sandwich_id     INT NOT NULL REFERENCES sandwich (id),
    ingredient_id   INT NOT NULL REFERENCES ingredient (id)
)