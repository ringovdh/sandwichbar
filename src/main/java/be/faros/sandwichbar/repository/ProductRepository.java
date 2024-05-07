package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product p WHERE p.product_type = 'DRINK'",nativeQuery = true)
    List<Drink> findAllDrinks();

    @Query(value = "SELECT * FROM product p WHERE p.product_type = 'SANDWICH'",nativeQuery = true)
    List<Sandwich> findAllSandwiches();
}
