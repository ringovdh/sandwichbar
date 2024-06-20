package be.faros.sandwichbar.dto.request;

import be.faros.sandwichbar.entity.Product;

public record CreateOrderItemDTO(
        int quantity,
        Product product) { }
