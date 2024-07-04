package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllAvailableProducts();
}
