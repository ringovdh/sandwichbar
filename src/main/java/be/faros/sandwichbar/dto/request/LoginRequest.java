package be.faros.sandwichbar.dto.request;

public record LoginRequest(
        String email,
        String password) {
}
