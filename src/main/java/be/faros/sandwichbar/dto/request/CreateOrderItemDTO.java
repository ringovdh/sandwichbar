package be.faros.sandwichbar.dto.request;

import be.faros.sandwichbar.dto.ProductDTO;

public record CreateOrderItemDTO(
        int quantity,
        ProductDTO product) { }
