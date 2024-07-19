Backend application for the sandwichbar app https://github.com/ringovdh/sandwichbar-front/tree/master

start db container: 
### `docker compose up`

build the application:
### `mvn clean compile`

start the application:
* no profile
* database is created by flyway script
* run init_data.sql script to add a few products
* users can be created by the frontend application (register functionality)
