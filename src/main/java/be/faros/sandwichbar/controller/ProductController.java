package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.response.GetProductsResponse;
import org.springframework.http.ResponseEntity;

public interface ProductController {

    ResponseEntity<GetProductsResponse> getAllProducts();
}
