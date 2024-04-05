package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.entity.Branch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static be.faros.sandwichbar.mother.AddressMother.createAddress;
import static be.faros.sandwichbar.mother.BranchMother.createBranch;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class BranchRepositoryTest extends RepositoryTest {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer testContainer = createContainer();

    @Autowired
    BranchRepository branchRepository;

    @Test
    @DisplayName("Create new Branch")
    public void createNewBranch() {
        Address address = createAddress();
        Branch branch = createBranch(address);

        branchRepository.save(branch);

        assertNotEquals(0, branch.getId());
        assertNotEquals(0, branch.getAddress().getId());
    }
}
