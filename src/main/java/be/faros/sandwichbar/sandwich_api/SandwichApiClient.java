package be.faros.sandwichbar.sandwich_api;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class SandwichApiClient {

    private final WebClient webClient;

    @Autowired
    public SandwichApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<ProductDTO> getSandwiches() {
        try {
            var response = webClient.get()
                    .uri("/sandwiches")
                    .retrieve().bodyToFlux(SandwichDTO.class);
            return response.toStream()
                    .map(ProductMapper::mapSandwichDTOToProductDTO)
                    .toList();
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return emptyList();
        }


    }

}
