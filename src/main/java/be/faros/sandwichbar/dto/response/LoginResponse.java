package be.faros.sandwichbar.dto.response;

public record LoginResponse(
        String name,
        String email,
        String token) { }
