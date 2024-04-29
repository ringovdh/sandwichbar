package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.SandwichDTO;
import be.faros.sandwichbar.entity.Sandwich;

import java.util.Arrays;

import static be.faros.sandwichbar.mother.IngredientMother.getCheddar;
import static be.faros.sandwichbar.mother.IngredientMother.getTomatoes;

public class SandwichMother {

    public static Sandwich getCheeseSandwich() {
        return new Sandwich(
                "CheeseSandwich",
                4.5,
                Arrays.asList(getTomatoes(), getCheddar()));
    }

    public static SandwichDTO createSandwichDTO(int id) {
        return new SandwichDTO(
                id,
                "CheeseSandwich",
                4.5,
                "6b65434b-af6e-48db-969f-a71558999aaf");
    }
}
