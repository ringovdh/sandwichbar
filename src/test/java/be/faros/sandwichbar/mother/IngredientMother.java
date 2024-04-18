package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.IngredientDTO;
import be.faros.sandwichbar.entity.Ingredient;

public class IngredientMother {

    private static final String CHEDDAR = "Cheddar";
    private static final String TOMATO = "Tomato";


    public static Ingredient getTomatoes() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(TOMATO);
        ingredient.setCategory("Vegetable");
        ingredient.setStock(10);
        return ingredient;
    }

    public static Ingredient getCheddar() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(CHEDDAR);
        ingredient.setCategory("Cheese");
        ingredient.setStock(10);
        return ingredient;
    }

    public static IngredientDTO getTomatoesDTO() {
        return new IngredientDTO(0, TOMATO);
    }

    public static IngredientDTO getCheddarDTO() {
        return new IngredientDTO(0, CHEDDAR);
    }
}
