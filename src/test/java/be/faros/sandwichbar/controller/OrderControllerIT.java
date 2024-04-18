package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.OrderRequest;
import be.faros.sandwichbar.dto.response.OrderResponse;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Ingredient;
import be.faros.sandwichbar.entity.Sandwich;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.mother.DrinkMother;
import be.faros.sandwichbar.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static be.faros.sandwichbar.mother.DrinkMother.getDrink;
import static be.faros.sandwichbar.mother.IngredientMother.getCheddar;
import static be.faros.sandwichbar.mother.IngredientMother.getTomatoes;
import static be.faros.sandwichbar.mother.OrderMother.createNewOrderRequest;
import static be.faros.sandwichbar.mother.SandwichMother.createSandwichDTO;
import static be.faros.sandwichbar.mother.SandwichMother.getSandwich;
import static be.faros.sandwichbar.mother.UserMother.createNewUserPino;

public class OrderControllerIT extends ControllerITBase {

    @Autowired
    OrderRepositoryTest orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    SandwichRepository sandwichRepository;
    @Autowired
    DrinkRepository drinkRepository;


    @Test
    @DisplayName("Create an order")
    public void createOrder() throws URISyntaxException {
        User user = createNewUserPino();
        Ingredient tomato = getTomatoes();
        Ingredient cheddar = getCheddar();
        Sandwich sandwich = getSandwich(Arrays.asList(tomato, cheddar));
        Drink drink = getDrink();

        userRepository.save(user);
        ingredientRepository.saveAll(Arrays.asList(tomato, cheddar));
        drinkRepository.save(drink);
        sandwichRepository.save(sandwich);

        OrderRequest orderRequest = createNewOrderRequest(user.getId(), createSandwichDTO(sandwich.getId()), DrinkMother.createDrinkDTO(drink.getId()));


        ResponseEntity<OrderResponse> response = restTemplate.postForEntity(new URI("/orders"), orderRequest, OrderResponse.class);

    }

}
