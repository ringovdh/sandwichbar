package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.entity.Branch;
import be.faros.sandwichbar.entity.Company;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.mother.UserMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Arrays;

import static be.faros.sandwichbar.mother.AddressMother.createAddress;
import static be.faros.sandwichbar.mother.BranchMother.createBranch;
import static be.faros.sandwichbar.mother.CompanyMother.createCompany;
import static be.faros.sandwichbar.mother.UserMother.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest extends RepositoryTestBase {
    @Autowired
    private UserRepository userRepository;

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
        assertNotEquals(0, user.getCompany().getId());
        assertNotEquals(0, user.getCompany().getBranch().getId());
        assertNotEquals(0, user.getCompany().getBranch().getAddress().getId());
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
        assertEquals(user1.getCompany().getId(), user2.getCompany().getId());
    }

}
