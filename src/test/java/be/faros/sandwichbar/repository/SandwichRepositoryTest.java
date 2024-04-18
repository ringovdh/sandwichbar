package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Sandwich;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SandwichRepositoryTest extends RepositoryTestBase {

    @Autowired
    SandwichRepository sandwichRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    @Sql(statements = """
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO sandwich(id, name, price) VALUES(1, 'Cheese sandwich', 4.5);
            INSERT INTO sandwich_ingredient(id, sandwich_id, ingredient_id) VALUES (1, 1, 1);
            INSERT INTO sandwich_ingredient(id, sandwich_id, ingredient_id) VALUES (2, 1, 2);
            """)
    @DisplayName("Find a Sandwich by id")
    public void findSandwichById() {
        Optional<Sandwich> sandwich = sandwichRepository.findById(1);

        assertTrue(sandwich.isPresent());
        assertEquals("Cheese sandwich", sandwich.get().getName());
        assertEquals(2, sandwich.get().getIngredients().size());
    }

    @Test
    @Sql(statements = """
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            """)
    @DisplayName("Create a new Sandwich")
    public void createNewSandwich() {
        Sandwich sandwich = new Sandwich();
        sandwich.setName("Cheese sandwich");
        sandwich.setPrice(4.5);
        sandwich.setIngredients(List.of(ingredientRepository.findById(1).get(), ingredientRepository.findById(2).get()));

        sandwichRepository.save(sandwich);

        assertNotEquals(0, sandwich.getId());

        Optional<Sandwich> result = sandwichRepository.findById(sandwich.getId());

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getIngredients().size());
    }


}
