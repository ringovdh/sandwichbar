package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static be.faros.sandwichbar.mother.OrderMother.createNewOrderItem_sandwich;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderRepositoryTest extends RepositoryTestBase {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    SandwichRepository sandwichRepository;

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, password, email) VALUES(1, 'Pino', 'pino@sesame.com', 'S&cret-10');
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO sandwich(id, name, price, product_id) VALUES (1, 'Cheese sandwich', 4.5, '6b65434b-af6e-48db-969f-a71558999aaf');
            INSERT INTO sandwich_ingredient(id, sandwich_id, ingredient_id) VALUES (1, 1, 1);
            INSERT INTO sandwich_ingredient(id, sandwich_id, ingredient_id) VALUES (2, 1, 2);
            INSERT INTO "order"(id, user_id, price) VALUES (1, 1, 4.5);
            INSERT INTO orderitem(id, order_id, price, quantity, sandwich_id) VALUES(1, 1, 4.5, 1, 1);
            """)
    @DisplayName("Find an Order by id")
    public void FindOrderById() {
        Optional<Order> order = orderRepository.findById(1);

        assertTrue(order.isPresent());
        assertEquals(1, order.get().getItems().size());
        assertNotNull(order.get().getItems().getFirst().getSandwich());
        assertEquals(2, order.get().getItems().getFirst().getSandwich().getIngredients().size());
        assertNotNull(order.get().getUser());
    }

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, password, email) VALUES(1, 'Pino', 'pino@sesame.com', 'S&cret-10');
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO sandwich(id, name, price, product_id) VALUES (1, 'Cheese sandwich', 4.5, '6b65434b-af6e-48db-969f-a71558999aaf');
            INSERT INTO sandwich_ingredient(id, sandwich_id, ingredient_id) VALUES (1, 1, 1);
            INSERT INTO sandwich_ingredient(id, sandwich_id, ingredient_id) VALUES (2, 1, 2);
            """)
    @DisplayName("Create a new Order")
    public void CreateNewOrder() {
        Order order = new Order();
        order.setUser(userRepository.findById(1).get());
        order.setItems(List.of(createNewOrderItem_sandwich(sandwichRepository.findById(1).get())));

        orderRepository.save(order);

        assertNotEquals(0, order.getId());

        Optional<Order> result = orderRepository.findById(order.getId());
        assertTrue(result.isPresent());
        assertNotNull(result.get().getUser());
        assertEquals(1, result.get().getItems().size());

    }
}
