package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.entity.Branch;
import be.faros.sandwichbar.entity.Company;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static be.faros.sandwichbar.mother.AddressMother.createAddress;
import static be.faros.sandwichbar.mother.BranchMother.createBranch;
import static be.faros.sandwichbar.mother.CompanyMother.createCompany;
import static org.junit.jupiter.api.Assertions.*;

public class CompanyRepositoryTest extends RepositoryTestBase {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    @Sql(statements = """
            INSERT INTO address(id, street, housenumber, postcode, city) VALUES(1, 'Sesam Street', 10, 1000, 'New York');
            INSERT INTO branch(id, name, address_id) VALUES(1, 'Sesame Street', 1);
            INSERT INTO company(id, name, branch_id) VALUES (1, 'Sesame workshop', 1);
            """)
    @DisplayName("Find a Company by id")
    public void findCompanyById() {
        Optional<Company> company = companyRepository.findById(1);

        assertTrue(company.isPresent());
        assertNotNull(company.get().getBranch());
        assertNotNull(company.get().getBranch().getAddress());
    }

    @Test
    @DisplayName("Create a new Company")
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
