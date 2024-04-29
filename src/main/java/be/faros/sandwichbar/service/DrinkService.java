package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.response.GetDrinksResponse;

public interface DrinkService {

    GetDrinksResponse findAllAvailableDrinks();
}
