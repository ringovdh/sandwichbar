package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.mother.UserMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest extends RepositoryTestBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql(statements = "INSERT INTO \"user\"(name, username, email, user_ref) VALUES('Pino', 'pino', 'pino@sesame.com', 'oAuth|1234')")
    @Transactional
    @DisplayName("Find a user by userref")
    public void findUserByUserRef() {
        Optional<User> user = userRepository.findByUserRef("oAuth|1234");

        assertTrue(user.isPresent());
        assertNull(user.get().getAddress());
        assertEquals("Pino", user.get().getName());
    }

    @Test
    @Sql(statements = """
            INSERT INTO address(street, housenumber, postcode, city) VALUES('Sesam Street', 10, 1000, 'New York');
            INSERT INTO "user"(name, username, email, user_ref, address_id) VALUES('Pino', 'pino', 'pino@sesame.com', 'oAuth|1234', 1);
            """)
    @Transactional
    @DisplayName("Find a user by userref")
    public void findUserWithAddressByUserRef() {
        Optional<User> user = userRepository.findWithAddressByUserRef("oAuth|1234");

        assertTrue(user.isPresent());
        assertNotNull(user.get().getAddress());
        assertEquals("Pino", user.get().getName());
        assertEquals("New York", user.get().getAddress().getCity());
    }

    @Test
    @DisplayName("Create new User")
    @Transactional
    public void createNewUser() {
        User user = UserMother.createNewUser1();

        int savedUser = userRepository.save(user);

        assertNotEquals(0, savedUser);
    }

}
