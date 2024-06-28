CREATE TABLE "order"
(
    order_id    INT IDENTITY(1,1) PRIMARY KEY,
    user_id     INT NOT NULL REFERENCES "user" (user_id)
)