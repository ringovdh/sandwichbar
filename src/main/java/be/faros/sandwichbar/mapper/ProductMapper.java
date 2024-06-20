package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;
import be.faros.sandwichbar.sandwich_api.SandwichDTO;

import java.util.ArrayList;

public class ProductMapper {

    public static ProductDTO mapProductToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getProductRef(),
                product.getPrice(),
                ProductType.DRINK.name(),
                new ArrayList<>()
        );
    }

    public ProductDTO mapDrinkToProductDTO(Drink drink) {
        return new ProductDTO(
                drink.getId(),
                drink.getName(),
                drink.getProductRef(),
                drink.getPrice(),
                ProductType.DRINK.name(),
                new ArrayList<>()
        );
    }

    public static ProductDTO mapSandwichDTOToProductDTO(SandwichDTO sandwich) {
        return new ProductDTO(
                sandwich.id(),
                sandwich.name(),
                sandwich.productRef(),
                sandwich.price(),
                ProductType.SANDWICH.name(),
                sandwich.ingredients()
        );
    }
}
