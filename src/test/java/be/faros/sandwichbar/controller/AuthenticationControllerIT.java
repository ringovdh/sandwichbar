package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.RegisterRequest;
import be.faros.sandwichbar.dto.response.LoginResponse;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.repository.UserRepository;
import be.faros.sandwichbar.service.AuthenticationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static be.faros.sandwichbar.mother.UserMother.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class AuthenticationControllerIT {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer testContainer = new PostgreSQLContainer("postgres:16.2-alpine3.19");

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Test
    @DisplayName("Register a new user")
    public void registerUser() throws URISyntaxException {
        RegisterRequest registerRequest = createRegisterRequestTommy();
        ResponseEntity<Void> response = restTemplate.postForEntity(new URI("/authenticate/register"), registerRequest, Void.class);
        Optional<User> result = userRepository.findByEmail(registerRequest.email());

        assertTrue(result.isPresent());
        assertEquals(registerRequest.email(), result.get().getEmail());
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());

    }

    @Test
    @DisplayName("Log in user")
    public void loginUser() throws URISyntaxException {
        authenticationService.registerUser(createRegisterRequestPino());

        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(new URI("/authenticate/login"), createLoginRequestPino(), LoginResponse.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertNotNull(response.getBody().token());
    }

}
