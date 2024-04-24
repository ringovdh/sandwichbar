package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.response.GetSandwichesResponse;
import org.springframework.http.ResponseEntity;

public interface SandwichController {

    ResponseEntity<GetSandwichesResponse> getSandwiches();
}
