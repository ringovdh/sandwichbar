package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.entity.Order;

public class OrderMapper {

    private final OrderItemMapper orderItemMapper = new OrderItemMapper();


    public GetOrderResponse mapToGetOrderResponse(Order order) {
        return new GetOrderResponse(
                order.getId(),
                order.getItems().stream()
                        .map(orderItemMapper::maptoOrderItemDTO).toList(),
                order.getOrderTotal(),
                order.getUser().getEmail()
        );
    }
}
