package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.SandwichDTO;
import be.faros.sandwichbar.entity.Ingredient;
import be.faros.sandwichbar.entity.Sandwich;

import java.util.List;

public class SandwichMother {

    public static Sandwich getSandwich(List<Ingredient> ingredients) {
        Sandwich sandwich = new Sandwich();
        sandwich.setName("CheeseSandwich");
        sandwich.setIngredients(ingredients);
        sandwich.setPrice(4);

        return  sandwich;
    }

    public static SandwichDTO createSandwichDTO(int id) {
        return new SandwichDTO(id);
    }
}
