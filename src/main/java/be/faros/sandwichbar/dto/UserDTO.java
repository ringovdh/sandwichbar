package be.faros.sandwichbar.dto;

public record UserDTO(
        int id,
        String name,
        String email,
        String password) {
}
