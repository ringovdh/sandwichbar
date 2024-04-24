package be.faros.sandwichbar.dto;

public record AddressDTO(
        int id,
        String street,
        String houseNumber,
        int postcode,
        String city) { }
