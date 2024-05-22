package be.faros.sandwichbar.dto.request;

import be.faros.sandwichbar.dto.AddressDTO;

import java.util.List;

public record CreateOrderRequest(
        List<CreateOrderItemDTO> items,
        AddressDTO deliveryAddress) {
}
