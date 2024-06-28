CREATE TABLE address
(
    address_id      INT IDENTITY(1,1) PRIMARY KEY,
    city            VARCHAR(255),
    street          VARCHAR(255),
    postcode        INT,
    housenumber     VARCHAR(5)
)