package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.mother.DrinkMother;
import be.faros.sandwichbar.repository.DrinkRepository;
import be.faros.sandwichbar.repository.IngredientRepository;
import be.faros.sandwichbar.repository.OrderRepository;
import be.faros.sandwichbar.repository.SandwichRepository;
import be.faros.sandwichbar.repository.UserRepository;
import be.faros.sandwichbar.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.net.URI;
import java.net.URISyntaxException;

import static be.faros.sandwichbar.mother.OrderMother.createNewOrderRequest;
import static be.faros.sandwichbar.mother.SandwichMother.createSandwichDTO;
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
    SandwichRepository sandwichRepository;
    @Autowired
    DrinkRepository drinkRepository;


    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, password, email) VALUES(1, 'Pino', 'pino@sesame.com', 'S&cret-10');
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO sandwich(id, name, price, product_id) VALUES (1, 'Cheese sandwich', 4.5, '');
            INSERT INTO drink(id, name, price, stock, product_id) VALUES (1, 'Cola', 2.5, 5, '');
            INSERT INTO sandwich_ingredient(id, sandwich_id, ingredient_id) VALUES (1, 1, 1);
            INSERT INTO sandwich_ingredient(id, sandwich_id, ingredient_id) VALUES (2, 1, 2);
            """)
    @DisplayName("Create an order")
    public void createOrder() throws URISyntaxException {
        CreateOrderRequest createOrderRequest = createNewOrderRequest(1, createSandwichDTO(1), DrinkMother.createDrinkDTO());

        ResponseEntity<CreateOrderResponse> response = restTemplate.postForEntity(new URI("/orders"), createOrderRequest, CreateOrderResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().id());

        GetOrderResponse order = orderService.findById(response.getBody().id());
        assertEquals(2, order.items().size());
    }

}
