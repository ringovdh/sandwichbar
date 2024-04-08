package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
