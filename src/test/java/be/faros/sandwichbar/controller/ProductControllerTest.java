package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.response.GetProductsResponse;
import be.faros.sandwichbar.security.OAuth2loginSuccessHandler;
import be.faros.sandwichbar.security.SecurityConfig;
import be.faros.sandwichbar.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(SecurityConfig.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OAuth2loginSuccessHandler loginSuccessHandler;

    @MockBean
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Get all products")
    void getAllProducts_success() throws Exception {
        when(productService.findAllAvailableProducts()).thenReturn(new GetProductsResponse(emptyList()));

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn();

        verify(productService).findAllAvailableProducts();
    }

}