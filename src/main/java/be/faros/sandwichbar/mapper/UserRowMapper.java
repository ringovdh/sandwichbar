package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.entity.Address;
import be.faros.sandwichbar.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    AddressRowMapper addressRowMapper = new AddressRowMapper();

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setUserRef(rs.getString("user_ref"));
        user.setAddress(mapAddress(rs, rowNum));
        return user;
    }

    private Address mapAddress(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("address_id");
        if (rs.getInt("address_id") != 0) {
            return addressRowMapper.mapRow(rs, rowNum);
        }
        return null;
    }

}
