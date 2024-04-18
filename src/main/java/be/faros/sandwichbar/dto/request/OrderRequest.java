package be.faros.sandwichbar.dto.request;

import be.faros.sandwichbar.dto.OrderItemDTO;

import java.util.List;

public record OrderRequest(
        int userId,
        List<OrderItemDTO> items) {
}
