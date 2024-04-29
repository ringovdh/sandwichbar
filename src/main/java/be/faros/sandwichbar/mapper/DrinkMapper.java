package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.DrinkDTO;
import be.faros.sandwichbar.entity.Drink;

public class DrinkMapper {
    public DrinkDTO mapToDTO(Drink drink) {
        return new DrinkDTO(
                1,
                drink.getProductId()
        );
    }
}
