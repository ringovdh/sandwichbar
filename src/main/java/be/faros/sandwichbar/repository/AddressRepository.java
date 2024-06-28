package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository extends BasicRepository {


    @Autowired
    public AddressRepository(NamedParameterJdbcTemplate template) {
        super(template);
    }

    public Address save(Address address) {
        return new Address();
    }
}
