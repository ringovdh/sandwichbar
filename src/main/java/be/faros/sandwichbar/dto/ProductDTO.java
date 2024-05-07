package be.faros.sandwichbar.dto;

import java.util.List;

public record ProductDTO(
        int id,
        String name,
        double price,
        String productType,
        int stock,
        List<IngredientDTO> ingredients) {
}
