package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.mother.AddressMother;
import be.faros.sandwichbar.mother.UserMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest extends RepositoryTestBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql(statements = """
            INSERT INTO address(id, street, housenumber, postcode, city) VALUES(1, 'Sesam Street', 10, 1000, 'New York');
            INSERT INTO "user"(id, name, username, email, user_ref, address_id) VALUES(1, 'Pino', 'pino', 'pino@sesame.com', 'oAuth|1234', 1);
            """)
    @DisplayName("Find a user by id")
    public void findUserByUserId() {
        Optional<User> user = userRepository.findById(1);

        assertTrue(user.isPresent());
        assertNotNull(user.get().getAddress());
    }

    @Test
    @DisplayName("Create new User with address")
    public void createNewUser() {
        User user = UserMother.createNewUser1();
        user.setAddress(AddressMother.createAddress());

        User savedUser = userRepository.save(user);

        assertNotEquals(0, savedUser.getId());
        assertNotEquals(0, savedUser.getAddress().getId());
    }

}
