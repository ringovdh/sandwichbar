package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static be.faros.sandwichbar.mother.AddressMother.createAddress;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddressRepositoryTest extends RepositoryTest {

    @Container
    @ServiceConnection
    public static PostgreSQLContainer testContainer = createContainer();

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
