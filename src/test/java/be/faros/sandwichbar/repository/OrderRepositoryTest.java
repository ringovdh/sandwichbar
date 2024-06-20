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
import static be.faros.sandwichbar.mother.ProductMother.createImportedProduct;
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
            INSERT INTO "order"(id, user_id) VALUES (1, 1);
            INSERT INTO orderitem(id, order_id, quantity, product_ref) VALUES(1, 1, 1, 'PRD_001');
            """)
    @DisplayName("Find an Order by id")
    public void FindOrderById() {
        Optional<Order> order = orderRepository.findById(1);

        assertTrue(order.isPresent());
        assertEquals(1, order.get().getItems().size());
        assertNotNull(order.get().getItems().getFirst().getProductRef());
        assertNotNull(order.get().getUser());
    }

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(1, 'Pino', 'pino', 'pino@sesame.com', 'oAuth|1234');
            """)
    @DisplayName("Create a new Order")
    public void CreateNewOrder() {
        Order order = new Order();
        order.setUser(userRepository.findById(1).get());
        OrderItem orderItem = createNewOrderItem(createImportedProduct());
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
