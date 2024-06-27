package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.ProductType;
import be.faros.sandwichbar.sandwich_api.SandwichDTO;

import java.util.ArrayList;

public class ProductMapper {

    public static ProductDTO mapProductToDTO(Product product) {
        return new ProductDTO(
                product.getName(),
                product.getProductRef(),
                product.getPrice(),
                product.getProductType(),
                new ArrayList<>()
        );
    }


    public static ProductDTO mapSandwichDTOToProductDTO(SandwichDTO sandwich) {
        return new ProductDTO(
                sandwich.name(),
                sandwich.productRef(),
                sandwich.price(),
                ProductType.SANDWICH.name(),
                sandwich.ingredients()
        );
    }
}
