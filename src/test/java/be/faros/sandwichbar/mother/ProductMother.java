package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;

import static be.faros.sandwichbar.mapper.ProductMapper.mapProductToDTO;

public class ProductMother {

    public static Product createImportedProduct() {
        Product product = new Product();
        product.setName("CheeseSandwich");
        product.setProductRef("PRD-001");
        product.setPrice(4.5);
        product.setStock(3);
        product.setProductType(ProductType.SANDWICH.name());
        return product;
    }

    public static ProductDTO createImportedProductDTO() {
        return mapProductToDTO(createImportedProduct());
    }

    public static Product createNewDrink() {
        Product product = new Product();
        product.setName("Coca-Cola");
        product.setProductRef("DRK-001");
        product.setPrice(2.5);
        product.setStock(3);
        product.setProductType(ProductType.DRINK.name());
        return product;
    }

    public static Product createExistingDrink() {
        Product drink = createNewDrink();
        drink.setId(20);
        return drink;
    }

    public static Product createExistingDrinkOutOfStock() {
        Product drink = createNewDrink();
        drink.setId(20);
        drink.setStock(0);
        return drink;
    }

}
