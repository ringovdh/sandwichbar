package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.dto.response.GetProductsResponse;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;
import be.faros.sandwichbar.entity.Sandwich;
import be.faros.sandwichbar.mapper.ProductMapper;
import be.faros.sandwichbar.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productMapper = new ProductMapper();
    }


    @Transactional(readOnly = true)
    @Override
    public GetProductsResponse findAllProducts() {
        List<Product> products = productRepository.findAll();

        return new GetProductsResponse(products.stream()
                .map(productMapper::mapToDTO).toList());
    }

    @Transactional(readOnly = true)
    @Override
    public GetProductsResponse findAllAvailableProducts() {
        List<Product> avaliableProducts = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        avaliableProducts.addAll(products.stream()
                .filter(p -> p.getProductType().equals(ProductType.DRINK.name()))
                .map(p -> (Drink)p)
                .filter(d -> d.getStock() > 0).toList());
        avaliableProducts.addAll(products.stream()
                .filter(p -> p.getProductType().equals(ProductType.SANDWICH.name()))
                .map(p -> (Sandwich)p)
                .filter(Sandwich::isAvailable).toList());

        return new GetProductsResponse(avaliableProducts.stream()
                .map(productMapper::mapToDTO).toList());
    }

}
