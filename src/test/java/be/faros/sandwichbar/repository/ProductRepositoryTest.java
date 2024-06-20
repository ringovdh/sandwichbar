package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRepositoryTest extends RepositoryTestBase {

    @Autowired
    ProductRepository productRepository;

        @Test
    @Sql(statements = "INSERT INTO product(id, name, product_ref, price, stock, product_type) VALUES(1, 'Coca-Cola', 'DRK-0001', 3.2, 3, 'DRINK')")
    @DisplayName("Find a Drink by id")
    public void findDrinkById() {
        Optional<Product> product = productRepository.findById(1);

        assertTrue(product.isPresent());
        assertEquals(Drink.class, product.get().getClass());
        assertEquals(ProductType.DRINK.name(), product.get().getProductType());
        Drink drink = (Drink) product.get();
        assertEquals("Coca-Cola", drink.getName());
    }

    @Test
    @DisplayName("Create a new Drink")
    public void createNewDrink() {
        Drink drink = new Drink(
                "Coca-Cola",
                "DRK-0001",
                3.2,
                3);

        productRepository.save(drink);

        assertNotEquals(0, drink.getId());

        Optional<Product> result = productRepository.findById(drink.getId());

        assertTrue(result.isPresent());
        assertEquals(Drink.class, result.get().getClass());
        assertEquals(ProductType.DRINK.name(), result.get().getProductType());
        Drink resultAsDrink = (Drink) result.get();
        assertEquals(3, resultAsDrink.getStock());
    }


}
