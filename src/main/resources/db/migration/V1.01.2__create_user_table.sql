CREATE TABLE "user"
(
    user_id     INT IDENTITY(1,1) PRIMARY KEY,
    name        VARCHAR(255),
    username    VARCHAR(255),
    email       VARCHAR(255),
    user_ref    VARCHAR(255),
    address_id  INT REFERENCES address (address_id)
)