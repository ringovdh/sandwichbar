package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.response.GetProductsResponse;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;
import be.faros.sandwichbar.mapper.ProductMapper;
import be.faros.sandwichbar.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Product> products = productRepository.findAll();
        // TODO filter on stock
        return new GetProductsResponse(products.stream()
                .map(productMapper::mapToDTO).toList());
    }

    @Transactional(readOnly = true)
    @Override
    public GetProductsResponse findAllAvailableProducts2() {
        List<Drink> drinks = productRepository.findAllDrinks(ProductType.DRINK.name());
        List<Drink> drinks = productRepository.findAllDrinks(ProductType.DRINK.name());
        // TODO filter on stock
        return new GetProductsResponse(products.stream()
                .map(productMapper::mapToDTO).toList());
    }
}
