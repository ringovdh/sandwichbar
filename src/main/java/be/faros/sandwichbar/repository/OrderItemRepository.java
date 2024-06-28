package be.faros.sandwichbar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepository extends BasicRepository {

    @Autowired
    public OrderItemRepository(NamedParameterJdbcTemplate template) {
        super(template);
    }
}
