package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.response.GetProductsResponse;

public interface ProductService {

    GetProductsResponse findAllAvailableProducts();
}
