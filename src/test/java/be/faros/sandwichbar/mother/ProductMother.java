package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;

import static be.faros.sandwichbar.mapper.ProductMapper.mapProductToDTO;

public class ProductMother {

    public static Product createImportedProduct() {
        return new Product(
                "CheeseSandwich",
                "PRD-001",
                4.5);
    }

    public static ProductDTO createImportedProductDTO() {
        return mapProductToDTO(createImportedProduct());
    }

    public static Drink createNewDrink() {
        return new Drink(
                "Coca-Cola",
                "DRK-0001",
                2.5,
                5);
    }

    public static Product createExistingDrink() {
        Drink drink = createNewDrink();
        drink.setId(20);
        return drink;
    }

    public static Product createExistingDrinkOutOfStock() {
        Drink drink = createNewDrink();
        drink.setId(20);
        drink.setStock(0);
        return drink;
    }

}
