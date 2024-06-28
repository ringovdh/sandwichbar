package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository extends BasicRepository {

    @Autowired
    public ProductRepository(NamedParameterJdbcTemplate template) {
        super(template);
    }

    public Optional<Product> findById(int i) {
        return Optional.empty();
    }

    public void save(Product drink) {
    }

    public Optional<Product> findByProductRef(String productRef) {
        return Optional.empty();
    }

    public List<Product> findAll() {
        return List.of();
    }

}
