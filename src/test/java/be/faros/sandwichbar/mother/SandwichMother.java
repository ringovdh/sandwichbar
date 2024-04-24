package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.SandwichDTO;
import be.faros.sandwichbar.entity.Sandwich;

import java.util.Arrays;

import static be.faros.sandwichbar.mother.IngredientMother.getCheddar;
import static be.faros.sandwichbar.mother.IngredientMother.getTomatoes;

public class SandwichMother {

    public static Sandwich getCheeseSandwich() {
        Sandwich sandwich = new Sandwich();
        sandwich.setName("CheeseSandwich");
        sandwich.setIngredients(Arrays.asList(getTomatoes(), getCheddar()));
        sandwich.setPrice(4.5);

        return  sandwich;
    }

    public static SandwichDTO createSandwichDTO(int id) {
        return new SandwichDTO(
                id,
                "CheeseSandwich",
                4.5);
    }
}
