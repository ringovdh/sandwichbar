package be.faros.sandwichbar.dto.response;

import be.faros.sandwichbar.dto.DrinkDTO;

import java.util.List;

public record GetDrinksResponse(
        List<DrinkDTO> drinks) { }
