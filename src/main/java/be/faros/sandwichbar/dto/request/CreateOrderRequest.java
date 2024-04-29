package be.faros.sandwichbar.dto.request;

import be.faros.sandwichbar.dto.AddressDTO;

import java.util.List;

public record CreateOrderRequest(
        int userId,
        List<CreateOrderItemDTO> items,
        AddressDTO deliveryAddress) {
}
