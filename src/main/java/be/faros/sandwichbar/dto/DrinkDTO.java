package be.faros.sandwichbar.dto;

public record DrinkDTO(
        String name,
        double price,
        int quantity,
        String productId) { }
