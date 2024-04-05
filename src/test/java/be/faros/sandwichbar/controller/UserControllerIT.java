package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.UserDTO;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.net.URISyntaxException;

import static be.faros.sandwichbar.mother.UserMother.createNewUserPinoDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserControllerIT {
    @Container
    @ServiceConnection
    public static PostgreSQLContainer testContainer = new PostgreSQLContainer("postgres:16.2-alpine3.19");

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void registerUser() throws URISyntaxException {
        UserDTO user = createNewUserPinoDTO();

        UserDTO userDTO = restTemplate.postForObject(new URI("/users"), user, UserDTO.class);
        assertEquals(1, userDTO.id());

        User result = userRepository.getReferenceById(userDTO.id());
        assertNotNull(result);
    }


}
