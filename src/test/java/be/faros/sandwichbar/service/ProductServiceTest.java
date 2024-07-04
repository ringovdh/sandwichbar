package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.repository.ProductRepository;
import be.faros.sandwichbar.sandwich_api.SandwichApiClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static be.faros.sandwichbar.mother.ProductMother.createExistingDrink;
import static be.faros.sandwichbar.mother.ProductMother.createImportedProductDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest extends SandwichbarTestBase {

    @Mock
    ProductRepository productRepository;

    @Mock
    SandwichApiClient sandwichApiClient;

    @InjectMocks
    ProductServiceImpl productService;


    @Test
    @DisplayName("Find all available products")
    public void findAllProducts() {
        Product drink = createExistingDrink();
        ProductDTO sandwich = createImportedProductDTO();

        when(productRepository.findAll()).thenReturn(List.of(drink));
        when(sandwichApiClient.getSandwiches()).thenReturn(List.of(sandwich));

        List<ProductDTO> availableProducts = productService.findAllAvailableProducts();

        verify(productRepository).findAll();
        assertEquals(2, availableProducts.size());
    }

}