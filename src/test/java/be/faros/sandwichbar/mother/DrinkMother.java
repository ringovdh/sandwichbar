package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.DrinkDTO;
import be.faros.sandwichbar.entity.Drink;

public class DrinkMother {

    public static Drink getDrink() {
        return new Drink("Coca-Cola", 2, 5);
    }

    public static DrinkDTO createDrinkDTO() {
        return new DrinkDTO("Coca-Cola", 2, 5, "6b65434b-af6e-48db-969f-a71558999aaf");
    }
}
