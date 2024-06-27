package be.faros.sandwichbar.integration;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.dto.response.GetProductsResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static be.faros.sandwichbar.mother.ProductMother.createImportedProductDTO;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIT extends ControllerITBase {

    private static WireMockServer wireMockServer;

    @BeforeEach
    void init() {
        wireMockServer = new WireMockServer(new WireMockConfiguration().port(8084));
        wireMockServer.start();
        WireMock.configureFor("sandwich-api", 8084);
    }



    @Test
    @Transactional
    @Sql(statements = """
            INSERT INTO product(id, name, product_ref, price, stock, product_type) VALUES (20, 'Coca-Cola', 'PRD-001', 2.5, 5, 'DRINK');
            """)
    @DisplayName("Get all products ")
    void getAllProducts() throws Exception {
        ProductDTO product = createImportedProductDTO();
        String valueAsJson = objectMapper.writeValueAsString(List.of(product));
        stubFor(get(urlMatching("/sandwiches"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBody(valueAsJson)));

        String result = mvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        wireMockServer.stop();
        GetProductsResponse response = objectMapper.readValue(result, GetProductsResponse.class);

        assertEquals(2, response.products().size());

    }

    @Test
    @Transactional
    @Sql(statements = """
            INSERT INTO product(id, name, product_ref, price, stock, product_type) VALUES (20, 'Coca-Cola', 'PRD-001', 2.5, 5, 'DRINK');
            """)
    @DisplayName("Get all products service not available")
    void getAllProducts_error() throws Exception {
        stubFor(get(urlMatching("/sandwiches"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withStatus(408)));

        String result = mvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        wireMockServer.stop();

        GetProductsResponse response = objectMapper.readValue(result, GetProductsResponse.class);

        assertEquals(1, response.products().size());
    }

}