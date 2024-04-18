package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.DrinkDTO;
import be.faros.sandwichbar.entity.Drink;

public class DrinkMother {

    public static Drink getDrink() {
        Drink drink = new Drink();
        drink.setName("Cola");
        drink.setPrice(2);
        drink.setStock(5);
        return drink;
    }

    public static DrinkDTO createDrinkDTO(int id) {
        return new DrinkDTO(id);
    }
}
