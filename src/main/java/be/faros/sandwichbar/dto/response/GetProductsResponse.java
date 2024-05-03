package be.faros.sandwichbar.dto.response;

import be.faros.sandwichbar.dto.ProductDTO;

import java.util.List;

public record GetProductsResponse(
        List<ProductDTO> products) { }
