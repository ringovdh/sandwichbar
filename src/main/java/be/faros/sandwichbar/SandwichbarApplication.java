package be.faros.sandwichbar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "be.faros.sandwichbar")
public class SandwichbarApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SandwichbarApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SandwichbarApplication.class, args);
    }

}
