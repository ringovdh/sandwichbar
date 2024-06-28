package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.entity.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();
        address.setId(rs.getInt("address_id"));
        address.setStreet(rs.getString("street"));
        address.setHouseNumber(rs.getString("housenumber"));
        address.setPostcode(rs.getInt("postcode"));
        address.setCity(rs.getString("city"));
        return address;
    }
}
