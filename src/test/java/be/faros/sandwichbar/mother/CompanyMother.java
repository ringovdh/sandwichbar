package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.entity.Branch;
import be.faros.sandwichbar.entity.Company;

public class CompanyMother {

    public static Company createCompany(Branch branch) {
        Company company = new Company();
        company.setName("Sesame workshop");
        company.setBranch(branch);
        return company;
    }
}
