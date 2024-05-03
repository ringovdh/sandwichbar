package be.faros.sandwichbar.dto;

public record ProductDTO(
        int id,
        String name,
        double price,
        String productType,
        int stock) {
}
