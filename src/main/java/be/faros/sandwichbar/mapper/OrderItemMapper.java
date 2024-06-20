package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.OrderItemDTO;
import be.faros.sandwichbar.entity.OrderItem;

public class OrderItemMapper {

    public OrderItemDTO maptoOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getProductRef()
        );
    }
}
