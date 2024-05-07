package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.Sandwich;

import java.util.ArrayList;

public class ProductMapper {

    private final IngredientMapper ingredientMapper = new IngredientMapper();

    public ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getProductType(),
                0,
                new ArrayList<>()
        );
    }

    public ProductDTO mapDrinkToProductDTO(Drink drink) {
        return new ProductDTO(
                drink.getId(),
                drink.getName(),
                drink.getPrice(),
                drink.getProductType(),
                drink.getStock(),
                new ArrayList<>()
        );
    }

    public ProductDTO mapSandwichToProductDTO(Sandwich sandwich) {
        return new ProductDTO(
                sandwich.getId(),
                sandwich.getName(),
                sandwich.getPrice(),
                sandwich.getProductType(),
                0,
                sandwich.getIngredients().stream().map(ingredientMapper::mapToDTO).toList()
        );
    }
}
