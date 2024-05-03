package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.response.GetProductsResponse;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static be.faros.sandwichbar.mother.ProductMother.createExistingCheeseSandwich;
import static be.faros.sandwichbar.mother.ProductMother.createExistingDrink;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest extends SandwichbarTestBase {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;


    @Test
    @DisplayName("Find all available products")
    public void findAllProducts() {
        Product sandwich = createExistingCheeseSandwich();
        Product drink = createExistingDrink();
        List<Product> products = List.of(sandwich, drink);

        when(productRepository.findAll()).thenReturn(products);

        GetProductsResponse availableProducts = productService.findAllAvailableProducts();

        verify(productRepository).findAll();
        assertEquals(2, availableProducts.products().size());
    }

}