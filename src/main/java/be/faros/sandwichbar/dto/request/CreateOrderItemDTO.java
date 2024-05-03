package be.faros.sandwichbar.dto.request;

public record CreateOrderItemDTO(
        int quantity,
        int  productId) { }
