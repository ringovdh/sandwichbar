package be.faros.sandwichbar.dto;

public record OrderItemDTO(
        int id,
        int quantity,
        ProductDTO product) { }
