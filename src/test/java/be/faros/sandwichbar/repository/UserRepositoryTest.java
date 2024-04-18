package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.entity.Branch;
import be.faros.sandwichbar.entity.Company;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.mother.UserMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.Optional;

import static be.faros.sandwichbar.mother.AddressMother.createAddress;
import static be.faros.sandwichbar.mother.BranchMother.createBranch;
import static be.faros.sandwichbar.mother.CompanyMother.createCompany;
import static be.faros.sandwichbar.mother.UserMother.createUserPinoWithCompany;
import static be.faros.sandwichbar.mother.UserMother.createUserTommyWithCompany;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest extends RepositoryTestBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql(statements = """
            INSERT INTO address(id, street, housenumber, postcode, city) VALUES(1, 'Sesam Street', 10, 1000, 'New York');
            INSERT INTO branch(id, name, address_id) VALUES(1, 'Sesame Street', 1);
            INSERT INTO company(id, name, branch_id) VALUES (1, 'Sesame workshop', 1);
            INSERT INTO "user"(id, name, password, email, company_id) VALUES (1, 'Pino', 'pino@sesame.com', 'S&cret-10', 1)
            
            """)
    @DisplayName("Find a Company by id")
    public void findCompanyById() {
        Optional<User> user = userRepository.findById(1);

        assertTrue(user.isPresent());
        assertNotNull(user.get().getCompany());
        assertNotNull(user.get().getCompany().getBranch());
        assertNotNull(user.get().getCompany().getBranch().getAddress());
    }

    @Test
    @DisplayName("Create new User")
    public void createNewUser() {
        User user = UserMother.createNewUserPino();

        userRepository.save(user);

        assertNotEquals(0, user.getId());
        assertNull(user.getCompany());
    }

    @Test
    @DisplayName("Create new User with company")
    public void createNewUserWithCompany() {
        Address address = createAddress();
        Branch branch = createBranch(address);
        Company company = createCompany(branch);
        User user = createUserPinoWithCompany(company);

        userRepository.save(user);

        assertNotEquals(0, user.getId());

        Optional<User> result = userRepository.findById(user.getId());
        assertTrue(result.isPresent());
        assertNotNull(result.get().getCompany());
        assertNotNull(result.get().getCompany().getBranch());
        assertNotNull(result.get().getCompany().getBranch().getAddress());
    }

    @Test
    @DisplayName("Create 2 new User with same company")
    public void create2NewUserWithSameCompany() {
        Address address = createAddress();
        Branch branch = createBranch(address);
        Company company = createCompany(branch);
        User user1 = createUserPinoWithCompany(company);
        User user2 = createUserTommyWithCompany(company);

        userRepository.saveAll(Arrays.asList(user1, user2));

        assertNotEquals(0, user1.getId());
        assertNotEquals(0, user2.getId());

        Optional<User> result1 = userRepository.findById(user1.getId());
        Optional<User> result2 = userRepository.findById(user2.getId());
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertEquals(result1.get().getCompany().getId(), result2.get().getCompany().getId());
    }

}
