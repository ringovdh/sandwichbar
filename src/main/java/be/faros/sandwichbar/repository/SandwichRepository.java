package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SandwichRepository extends JpaRepository<Sandwich, Integer> {

    List<Sandwich> findAllByIngredientsStockIsNotNull();
}
