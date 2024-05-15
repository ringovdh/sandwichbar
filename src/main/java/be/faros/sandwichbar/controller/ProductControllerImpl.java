package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.response.GetProductsResponse;
import be.faros.sandwichbar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Override
    public ResponseEntity<GetProductsResponse> getAllProducts() {
        return ResponseEntity.ok().body(productService.findAllAvailableProducts());
    }

}
