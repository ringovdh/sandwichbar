package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {


    Optional<Drink> findByProductId(String productId);
}
