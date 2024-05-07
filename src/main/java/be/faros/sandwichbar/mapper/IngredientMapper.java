package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.IngredientDTO;
import be.faros.sandwichbar.entity.Ingredient;

public class IngredientMapper {

    public IngredientDTO mapToDTO(Ingredient ingredient) {
        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName()
        );
    }
}
