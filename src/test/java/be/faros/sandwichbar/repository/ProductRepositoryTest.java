package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;
import be.faros.sandwichbar.entity.Sandwich;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRepositoryTest extends RepositoryTestBase {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    @Sql(statements = """
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO product(id, name, price, product_type) VALUES(1, 'Cheese sandwich', 4.5, 'SANDWICH');
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (1, 1, 1);
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (2, 1, 2);
            """)
    @DisplayName("Find a Sandwich by id")
    public void findSandwichById() {
        Optional<Product> product = productRepository.findById(1);

        assertTrue(product.isPresent());
        assertEquals(Sandwich.class, product.get().getClass());
        assertEquals("SANDWICH",product.get().getProductType());
        Sandwich sandwich = (Sandwich) product.get();
        assertEquals("Cheese sandwich", sandwich.getName());
        assertEquals(2, sandwich.getIngredients().size());
    }

    @Test
    @Sql(statements = """
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            """)
    @DisplayName("Create a new Sandwich")
    public void createNewSandwich() {
        Sandwich sandwich = new Sandwich(
                "Cheese sandwich",
                4.5,
                List.of(ingredientRepository.findById(1).get(), ingredientRepository.findById(2).get()));

        productRepository.save(sandwich);

        assertNotEquals(0, sandwich.getId());

        Optional<Product> result = productRepository.findById(sandwich.getId());

        assertTrue(result.isPresent());
        assertEquals(Sandwich.class, result.get().getClass());
        assertEquals("SANDWICH",result.get().getProductType());
        Sandwich resultAsSandwich = (Sandwich) result.get();
        assertEquals(2,resultAsSandwich.getIngredients().size());
    }

    @Test
    @Sql(statements = "INSERT INTO product(id, name, price, stock, product_type) VALUES(1, 'Coca-Cola', 3.2, 3, 'DRINK')")
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
