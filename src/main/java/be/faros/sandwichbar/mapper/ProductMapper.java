package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Product;

public class ProductMapper {

    public ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getProductType(),
                0
        );
    }

    public ProductDTO mapDrinkToProductDTO(Drink drink) {
        return new ProductDTO(
                drink.getId(),
                drink.getName(),
                drink.getPrice(),
                drink.getProductType(),
                drink.getStock()
        );
    }
}
