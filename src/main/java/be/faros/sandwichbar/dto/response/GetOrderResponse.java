package be.faros.sandwichbar.dto.response;

import be.faros.sandwichbar.dto.OrderItemDTO;

import java.util.List;

public record GetOrderResponse(
        int id,
        List<OrderItemDTO> items,
        double price) { }
