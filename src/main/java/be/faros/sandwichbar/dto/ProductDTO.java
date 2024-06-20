package be.faros.sandwichbar.dto;

import java.util.List;

public record ProductDTO(
        String name,
        String productRef,
        double price,
        String productType,
        List<IngredientDTO> ingredients) {
}
