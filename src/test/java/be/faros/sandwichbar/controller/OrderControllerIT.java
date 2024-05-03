package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.repository.IngredientRepository;
import be.faros.sandwichbar.repository.OrderRepository;
import be.faros.sandwichbar.repository.ProductRepository;
import be.faros.sandwichbar.repository.UserRepository;
import be.faros.sandwichbar.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import java.net.URI;
import java.net.URISyntaxException;

import static be.faros.sandwichbar.mother.OrderMother.createNewOrderRequest;
import static be.faros.sandwichbar.mother.ProductMother.createExistingCheeseSandwich;
import static be.faros.sandwichbar.mother.ProductMother.createExistingDrink;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderControllerIT extends ControllerITBase {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    ProductRepository productRepository;


    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, password, email) VALUES(1, 'Pino', 'pino@sesame.com', 'S&cret-10');
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO product(id, name, price, product_type) VALUES (1, 'Cheese sandwich', 4.5, 'SANDWICH');
            INSERT INTO product(id, name, price, stock, product_type) VALUES (2, 'Coca-Cola', 2.5, 5, 'DRINK');
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (1, 1, 1);
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (2, 1, 2);
            """)
    @WithMockUser("pino@sesame.com")
    @DisplayName("Create an order")
    public void createOrder() throws URISyntaxException {
        CreateOrderRequest createOrderRequest = createNewOrderRequest(
                1,
                createExistingCheeseSandwich(),
                createExistingDrink());

        ResponseEntity<CreateOrderResponse> response =
                restTemplate.postForEntity(new URI("/orders"), createOrderRequest, CreateOrderResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().id());

        GetOrderResponse order = orderService.findById(response.getBody().id());
        assertEquals(2, order.items().size());
    }

}
