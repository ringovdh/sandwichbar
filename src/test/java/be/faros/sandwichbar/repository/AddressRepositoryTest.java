package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static be.faros.sandwichbar.mother.AddressMother.createAddress;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddressRepositoryTest extends RepositoryTestBase {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Create new Address")
    public void createNewAddress() {
        Address address = createAddress();
        addressRepository.save(address);

        assertNotEquals(0, address.getId());
    }
}
