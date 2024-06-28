package be.faros.sandwichbar.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class RepositoryTestBase {

    @Container
    static MSSQLServerContainer container = new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server").acceptLicense();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", container::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", container::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", container::getPassword);
    }
}
