package be.faros.sandwichbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public abstract class ControllerITBase {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer testContainer = new PostgreSQLContainer("postgres:16.2-alpine3.19");

    @Autowired
    TestRestTemplate restTemplate;


}
