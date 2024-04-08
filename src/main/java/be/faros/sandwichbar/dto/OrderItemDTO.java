package be.faros.sandwichbar.dto;

public record OrderItemDTO(
        int id,
        int quantity,
        SandwichDTO sandwich,
        DrinkDTO drink) {


}
