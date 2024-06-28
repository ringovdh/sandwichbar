package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository extends BasicRepository {

    @Autowired
    public OrderRepository(NamedParameterJdbcTemplate template) {
        super(template);
    }

    public Optional<Order> findById(int i) {
        return Optional.empty();
    }

    public Order save(Order order) {
        return new Order();
    }

    public List<Order> findByUser_userRef(String ref) {
        return List.of();
    }

    public List<Order> findAll() {
        return List.of();
    }

}
