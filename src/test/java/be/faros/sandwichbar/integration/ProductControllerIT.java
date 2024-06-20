package be.faros.sandwichbar.integration;

import be.faros.sandwichbar.dto.response.GetProductsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static be.faros.sandwichbar.mother.ProductMother.createImportedProductDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProductControllerIT extends ControllerITBase {



    @Test
    @Sql(statements = """
            INSERT INTO product(id, name, product_ref, price, stock, product_type) VALUES (20, 'Coca-Cola', 'PRD-001', 2.5, 5, 'DRINK');
            """)
    @DisplayName("Get all products")
    void getAllProducts() throws Exception {
        String responseBody = objectMapper.writeValueAsString(List.of(createImportedProductDTO()));


        String result = mvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GetProductsResponse response = objectMapper.readValue(result, GetProductsResponse.class);

        assertEquals(2, response.products().size());
    }

}