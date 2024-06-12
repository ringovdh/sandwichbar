package be.faros.sandwichbar.dto;

import java.util.List;

public record UserinfoDTO(
        String userRef,
        String username,
        String userEmail,
        List<String> roles) { }
