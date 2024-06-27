package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static be.faros.sandwichbar.mother.ProductMother.createNewDrink;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRepositoryTest extends RepositoryTestBase {

    @Autowired
    ProductRepository productRepository;

        @Test
    @Sql(statements = "INSERT INTO product(id, name, product_ref, price, stock, product_type) VALUES(1, 'Coca-Cola', 'DRK-0001', 3.2, 3, 'DRINK')")
    @DisplayName("Find a Product by id")
    public void findProductById() {
        Optional<Product> product = productRepository.findById(1);

        assertTrue(product.isPresent());
        assertEquals(ProductType.DRINK.name(), product.get().getProductType());
        assertEquals("Coca-Cola", product.get().getName());
    }

    @Test
    @DisplayName("Create a new product")
    public void createNewProduct() {
        Product drink = createNewDrink();

        productRepository.save(drink);

        assertNotEquals(0, drink.getId());

        Optional<Product> result = productRepository.findById(drink.getId());

        assertTrue(result.isPresent());
        assertEquals(ProductType.DRINK.name(), result.get().getProductType());
        assertEquals(3, drink.getStock());
    }


}
