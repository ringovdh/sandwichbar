package be.faros.sandwichbar.dto;

import java.util.List;

public record OrderDTO(
        int id,
        UserDTO user,
        List<OrderItemDTO> items,
        double price) {
}
