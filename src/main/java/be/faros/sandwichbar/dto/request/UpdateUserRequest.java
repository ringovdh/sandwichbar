package be.faros.sandwichbar.dto.request;

import be.faros.sandwichbar.dto.AddressDTO;

public record UpdateUserRequest(
        String username,
        String fullName,
        AddressDTO address) {
}
