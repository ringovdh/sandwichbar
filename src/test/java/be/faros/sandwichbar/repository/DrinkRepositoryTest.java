package be.faros.sandwichbar.repository;

import be.faros.sandwichbar.entity.Drink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static be.faros.sandwichbar.mother.DrinkMother.getDrink;
import static org.junit.jupiter.api.Assertions.*;

public class DrinkRepositoryTest extends RepositoryTestBase {

    @Autowired
    DrinkRepository drinkRepository;

    @Test
    @Sql(statements = "INSERT INTO drink(id, name, price, stock) VALUES(1, 'Cola', 3.2, 3)")
    @DisplayName("Find a Drink by id")
    public void findDrinkById() {
        Optional<Drink> drink = drinkRepository.findById(1);

        assertTrue(drink.isPresent());
        assertEquals("Cola", drink.get().getName());
    }

    @Test
    @DisplayName("Create a new Drink")
    public void createNewDrink() {
        Drink drink = getDrink();

        drinkRepository.save(drink);

        assertNotEquals(0, drink.getId());
    }


}
