CREATE TABLE orderItem
(
    orderitem_id    INT NOT NULL PRIMARY KEY,
    order_id        INT NOT NULL REFERENCES "order" (order_id),
    quantity        INT,
    product_ref     VARCHAR(255)
)