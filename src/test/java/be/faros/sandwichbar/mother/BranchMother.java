package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.entity.Branch;

public class BranchMother {

    public static Branch createBranch(Address address) {
        Branch branch = new Branch();
        branch.setAddress(address);
        branch.setName("Sesame Street");
        return branch;
    }
}
