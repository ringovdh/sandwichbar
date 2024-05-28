package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.entity.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static be.faros.sandwichbar.mother.OrderMother.createNewOrderItem;
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
    ProductRepository productRepository;

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(1, 'Pino', 'pino', 'pino@sesame.com', 'oAuth|1234');
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO product(id, name, price, product_type) VALUES (1, 'Cheese sandwich', 4.5, 'SANDWICH');
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (1, 1, 1);
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (2, 1, 2);
            INSERT INTO "order"(id, user_id) VALUES (1, 1);
            INSERT INTO orderitem(id, order_id, quantity, product_id) VALUES(1, 1, 1, 1);
            """)
    @DisplayName("Find an Order by id")
    public void FindOrderById() {
        Optional<Order> order = orderRepository.findById(1);

        assertTrue(order.isPresent());
        assertEquals(1, order.get().getItems().size());
        assertNotNull(order.get().getItems().getFirst().getProduct());
        assertNotNull(order.get().getUser());
    }

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(1, 'Pino', 'pino', 'pino@sesame.com', 'oAuth|1234');
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO product(id, name, price, product_type) VALUES (1, 'Cheese sandwich', 4.5, 'SANDWICH');
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (1, 1, 1);
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (2, 1, 2);
            """)
    @DisplayName("Create a new Order")
    public void CreateNewOrder() {
        Order order = new Order();
        order.setUser(userRepository.findById(1).get());
        OrderItem orderItem = createNewOrderItem(productRepository.findById(1).get());
        orderItem.setOrder(order);
        order.setItems(List.of(orderItem));

        orderRepository.save(order);

        assertNotEquals(0, order.getId());

        Optional<Order> result = orderRepository.findById(order.getId());
        assertTrue(result.isPresent());
        assertNotNull(result.get().getUser());
        assertEquals(1, result.get().getItems().size());

    }
}
