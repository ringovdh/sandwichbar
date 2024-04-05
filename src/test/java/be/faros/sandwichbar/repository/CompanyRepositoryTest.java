package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.entity.Branch;
import be.faros.sandwichbar.entity.Company;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static be.faros.sandwichbar.mother.AddressMother.createAddress;
import static be.faros.sandwichbar.mother.BranchMother.createBranch;
import static be.faros.sandwichbar.mother.CompanyMother.createCompany;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class CompanyRepositoryTest extends RepositoryTest {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer testContainer = createContainer();

    @Autowired
    CompanyRepository companyRepository;

    @Test
    @DisplayName("Create new Company")
    public void createNewCompany() {
        Address address = createAddress();
        Branch branch = createBranch(address);
        Company company = createCompany(branch);

        companyRepository.save(company);

        assertNotEquals(0, company.getId());
        assertNotEquals(0, company.getBranch().getId());
        assertNotEquals(0, company.getBranch().getAddress().getId());
    }
}
