package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.DrinkDTO;
import be.faros.sandwichbar.entity.Drink;

public class DrinkMother {

    public static Drink getDrink() {
        return new Drink("Cola", 2, 5);
    }

    public static DrinkDTO createDrinkDTO(int id) {
        return new DrinkDTO(id);
    }
}
