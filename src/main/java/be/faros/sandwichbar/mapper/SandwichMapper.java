package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.SandwichDTO;
import be.faros.sandwichbar.entity.Sandwich;

public class SandwichMapper {
    public SandwichDTO mapToDTO(Sandwich sandwich) {
        return new SandwichDTO(
                sandwich.getId(),
                sandwich.getName(),
                sandwich.getPrice(),
                sandwich.getProductId()
        );
    }
}
