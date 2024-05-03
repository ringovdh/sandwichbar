package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.response.GetProductsResponse;

public interface ProductService {

    GetProductsResponse findAllProducts();
    GetProductsResponse findAllAvailableProducts();
    GetProductsResponse findAllAvailableProducts2();
}
