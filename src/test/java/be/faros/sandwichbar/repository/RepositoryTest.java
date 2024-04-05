package be.faros.sandwichbar.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Transactional
public class RepositoryTest {


    protected static PostgreSQLContainer createContainer() {
        return new PostgreSQLContainer("postgres:16.2-alpine3.19");
    }
}
