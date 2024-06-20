package be.faros.sandwichbar.sandwich_api;

import be.faros.sandwichbar.dto.IngredientDTO;

import java.util.List;

public record SandwichDTO(
        int id,
        String name,
        String productRef,
        List<IngredientDTO> ingredients,
        double price) {
}
