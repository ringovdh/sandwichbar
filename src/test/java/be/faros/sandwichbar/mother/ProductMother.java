package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.Sandwich;

import java.util.Arrays;

import static be.faros.sandwichbar.mother.IngredientMother.getCheddar;
import static be.faros.sandwichbar.mother.IngredientMother.getTomatoes;

public class ProductMother {

    public static Product createNewCheeseSandwich() {
        return new Sandwich(
                "CheeseSandwich",
                4.5,
                Arrays.asList(getTomatoes(), getCheddar()));
    }

    public static Product createExistingCheeseSandwich() {
        Product sandwich = createNewCheeseSandwich();
        sandwich.setId(10);
        return sandwich;
    }

    public static Drink createNewDrink() {
        return new Drink(
                "Coca-Cola",
                2.5,
                5);
    }

    public static Product createExistingDrink() {
        Drink drink = createNewDrink();
        drink.setId(20);
        return drink;
    }

    public static Product createExistingDrinkOutOfStock() {
        Drink drink = createNewDrink();
        drink.setId(20);
        drink.setStock(0);
        return drink;
    }

}
