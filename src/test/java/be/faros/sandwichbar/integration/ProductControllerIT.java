package be.faros.sandwichbar.integration;

import be.faros.sandwichbar.dto.response.GetProductsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIT extends ControllerITBase {

    @Test
    @Sql(statements = """
            INSERT INTO ingredient(id, category, name, stock) VALUES (1, 'Vegetables', 'Tomato', 3);
            INSERT INTO ingredient(id, category, name, stock) VALUES (2, 'Cheese', 'Cheddar', 5);
            INSERT INTO product(id, name, price, product_type) VALUES (10, 'Cheese sandwich', 4.5, 'SANDWICH');
            INSERT INTO product(id, name, price, stock, product_type) VALUES (20, 'Coca-Cola', 2.5, 5, 'DRINK');
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (1, 10, 1);
            INSERT INTO product_ingredient(id, product_id, ingredient_id) VALUES (2, 10, 2);
            """)
    @DisplayName("Get all products")
    void getAllProducts() throws Exception {

        String result = mvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GetProductsResponse response = objectMapper.readValue(result, GetProductsResponse.class);

        assertEquals(2, response.products().size());
    }

}