package be.faros.sandwichbar.integration;

import be.faros.sandwichbar.controller.ControllerTestBase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class ControllerITBase extends ControllerTestBase {

    @Container
    @ServiceConnection
    public static MSSQLServerContainer testContainer = new MSSQLServerContainer("mcr.microsoft.com/mssql/server");

}
