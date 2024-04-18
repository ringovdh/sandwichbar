package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {


}
