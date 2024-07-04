package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends ControllerTestBase {

    @MockBean
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Get all products")
    void getAllProducts_success() throws Exception {
        when(productService.findAllAvailableProducts()).thenReturn(emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn();

        verify(productService).findAllAvailableProducts();
    }

}