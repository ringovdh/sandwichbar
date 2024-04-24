package be.faros.sandwichbar.dto.response;

public record LoginResponse(
        int userId,
        String name,
        String email,
        String token) { }
