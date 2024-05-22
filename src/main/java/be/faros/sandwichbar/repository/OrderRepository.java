package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int userId);

    List<Order> findByUser_userRef(String ref);
}
