package be.faros.sandwichbar.dto;

import java.util.List;

public record SandwichDTO(
        int id,
        String name,
        double price,
        List<IngredientDTO> ingredients) {
}
