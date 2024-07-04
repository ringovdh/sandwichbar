package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;
import be.faros.sandwichbar.mapper.ProductMapper;
import be.faros.sandwichbar.repository.ProductRepository;
import be.faros.sandwichbar.sandwich_api.SandwichApiClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final SandwichApiClient sandwichApiClient;
    private final ProductRepository productRepository;

    public ProductServiceImpl(SandwichApiClient sandwichApiClient,
                              ProductRepository productRepository) {
        this.sandwichApiClient = sandwichApiClient;
        this.productRepository = productRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> findAllAvailableProducts() {
        List<ProductDTO> avaliableProducts = new ArrayList<>();

        avaliableProducts.addAll(getDrinks());
        avaliableProducts.addAll(sandwichApiClient.getSandwiches());

        return avaliableProducts;
    }

    private List<ProductDTO> getDrinks() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .filter(p -> p.getProductType().equals(ProductType.DRINK.name()))
                .filter(p -> p.getStock() > 0)
                .map(ProductMapper::mapProductToDTO)
                .toList();
    }

}
