CREATE TABLE delivery
(
    delivery_id     INT IDENTITY(1,1) PRIMARY KEY,
    status          VARCHAR(255),
    deliveryDate    DATE,
    address_id      INT NOT NULL REFERENCES address (address_id)
)