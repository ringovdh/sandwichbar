package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.response.GetDrinksResponse;
import org.springframework.http.ResponseEntity;

public interface DrinkController {

    ResponseEntity<GetDrinksResponse> getDrinks();
}
