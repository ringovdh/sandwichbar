package be.faros.sandwichbar.integration;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static be.faros.sandwichbar.mother.OrderMother.createNewCreateOrderItemDTO;
import static be.faros.sandwichbar.mother.ProductMother.createImportedProduct;
import static be.faros.sandwichbar.mother.ProductMother.createExistingDrink;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerIT extends ControllerITBase {

    @Autowired
    OrderRepository orderRepository;

    private final String GET_ORDERS_URL = "/orders/users";
    private final String GET_ALL_ORDERS_URL = "/orders";
    private final String GET_ORDER_URL = "/orders/";
    private final String CREATE_ORDER_URL = "/orders";

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(1, 'User from Sandwichbar', 'User', 'user@sandwich.be', 'user@sandwich.be');
            INSERT INTO product(id, name, product_ref, price, stock, product_type) VALUES (20, 'Coca-Cola', 'PRD-002', 2.5, 5, 'DRINK');
            """)
    @DisplayName("Create an order")
    @Transactional
    public void createOrder() throws Exception {
        CreateOrderRequest order = new CreateOrderRequest(List.of(
                createNewCreateOrderItemDTO(createImportedProduct()),
                createNewCreateOrderItemDTO(createExistingDrink())),null);
        String valueAsJson = objectMapper.writeValueAsString(order);

        String result = mvc.perform(MockMvcRequestBuilders.post(CREATE_ORDER_URL, order)
                        .with(oidcLogin().oidcUser(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        CreateOrderResponse response = objectMapper.readValue(result, CreateOrderResponse.class);
        assertNotEquals(0, response.id());

        Optional<Order> savedOrder = orderRepository.findById(response.id());
        assertTrue(savedOrder.isPresent());
        assertEquals(user.getEmail(), savedOrder.get().getUser().getEmail());
        assertEquals(2, savedOrder.get().getItems().size());
    }


    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(1, 'User from Sandwichbar', 'User', 'user@sandwich.be', 'user@sandwich.be');
            INSERT INTO "order"(id, user_id) VALUES (1, 1);
            INSERT INTO orderitem(id, order_id, quantity, product_ref) VALUES(1, 1, 1, 'PRD-001');
            INSERT INTO orderitem(id, order_id, quantity, product_ref) VALUES(2, 1, 1, 'PRD-002');
            """)
    @DisplayName("Get an order by id")
    @Transactional
    public void getOrder() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get(GET_ORDER_URL + 1)
                        .with(oidcLogin().oidcUser(user))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GetOrderResponse response = objectMapper.readValue(result, GetOrderResponse.class);
        assertEquals(1, response.id());
        assertEquals(user.getEmail(), response.client());
        assertEquals(2, response.items().size());
    }

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(1, 'User from Sandwichbar', 'User', 'user@sandwich.be', 'user@sandwich.be');
            INSERT INTO "order"(id, user_id) VALUES (1, 1);
            INSERT INTO "order"(id, user_id) VALUES (2, 1);
            INSERT INTO orderitem(id, order_id, quantity, product_ref) VALUES(1, 1, 1, 'PRD-001');
            INSERT INTO orderitem(id, order_id, quantity, product_ref) VALUES(2, 2, 1, 'PRD-002');
            """)
    @DisplayName("Get all orders by user")
    @Transactional
    public void getOrdersByUser() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get(GET_ORDERS_URL)
                        .with(oidcLogin().oidcUser(user))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GetOrdersResponse response = objectMapper.readValue(result, GetOrdersResponse.class);
        assertEquals(2, response.orders().size());
        assertEquals(user.getEmail(), response.orders().getFirst().client());
    }

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(1, 'User 1 from Sandwichbar', 'User1', 'user1@sandwich.be', 'user1@sandwich.be');
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(2, 'User 2 from Sandwichbar', 'User2', 'user2@sandwich.be', 'user2@sandwich.be');
            INSERT INTO "order"(id, user_id) VALUES (1, 1);
            INSERT INTO "order"(id, user_id) VALUES (2, 2);
            INSERT INTO orderitem(id, order_id, quantity, product_ref) VALUES(1, 1, 1, 'PRD-001');
            INSERT INTO orderitem(id, order_id, quantity, product_ref) VALUES(2, 2, 1, 'PRD-002');
            """)
    @DisplayName("Get all orders as admin")
    @Transactional
    public void getOrdersAsAdmin() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get(GET_ALL_ORDERS_URL)
                        .with(oidcLogin().oidcUser(admin))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GetOrdersResponse response = objectMapper.readValue(result, GetOrdersResponse.class);
        assertEquals(2, response.orders().size());
        assertNotEquals(response.orders().getLast().client(), response.orders().getFirst().client());
    }
}
