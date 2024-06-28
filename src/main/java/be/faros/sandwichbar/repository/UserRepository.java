package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository extends BasicRepository {

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate template) {
        super(template);
    }

    public int save(User user) {
        String query = "INSERT INTO \"user\" VALUES (:name, :username, :email, :userRef, :addressId)";
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("name", user.getName());
        parameterMap.put("username", user.getUsername());
        parameterMap.put("email", user.getEmail());
        parameterMap.put("userRef", user.getUserRef());
        parameterMap.put("addressId", user.getAddress() != null ? user.getAddress().getId() : null);
        return template.update(query, parameterMap);
    }

    public Optional<User> findByUserRef(String userRef) {
        String query = "SELECT * FROM \"user\" WHERE user_ref = :userRef";
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userRef", userRef);
        User user = template.queryForObject(query, parameterMap, new UserRowMapper());
        return Optional.of(user);
    }

    public Optional<User> findById(int id) {
        String query = "SELECT * FROM \"user\" WHERE user_id = :id";
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("id", id);
        User user = template.queryForObject(query, parameterMap, new UserRowMapper());
        return Optional.of(user);
    }

    public Optional<User> findWithAddressByUserRef(String userRef) {
        String query = "SELECT * FROM \"user\" u INNER JOIN address a on u.address_id = a.address_id WHERE user_ref = :userRef";
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userRef", userRef);
        User user = template.queryForObject(query, parameterMap, new UserRowMapper());
        return Optional.of(user);
    }
}
