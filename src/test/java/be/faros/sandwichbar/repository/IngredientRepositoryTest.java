package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Ingredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static be.faros.sandwichbar.mother.IngredientMother.getTomatoes;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientRepositoryTest extends RepositoryTestBase {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    @Sql(statements = "INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3)")
    @DisplayName("Find an Ingredient by id")
    public void findIngredientById() {
        Optional<Ingredient> ingredient = ingredientRepository.findById(1);

        assertTrue(ingredient.isPresent());
        assertEquals("Tomato", ingredient.get().getName());
    }

    @Test
    @DisplayName("Create a new Ingredient")
    public void createNewIngredient() {
        Ingredient tomato = getTomatoes();

        ingredientRepository.save(tomato);

        assertNotEquals(0, tomato.getId());
    }


}
