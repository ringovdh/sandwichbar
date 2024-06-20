package be.faros.sandwichbar.dto;

import java.util.List;

public record ProductDTO(
        int id,
        String name,
        String productRef,
        double price,
        String productType,
        List<IngredientDTO> ingredients) {
}
