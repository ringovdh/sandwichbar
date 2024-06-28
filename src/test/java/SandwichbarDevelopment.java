import be.faros.sandwichbar.SandwichbarApplication;
import org.junit.Ignore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MSSQLServerContainer;

@Ignore
@SpringBootTest
public class SandwichbarDevelopment {
    @Bean
    @RestartScope
    @ServiceConnection
    MSSQLServerContainer container() {
        return new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server");
    }

    public static void main(String[] args) {
        SpringApplication
                .from(SandwichbarApplication::main)
                .with(SandwichbarDevelopment.class)
                .run(args);
    }
}
