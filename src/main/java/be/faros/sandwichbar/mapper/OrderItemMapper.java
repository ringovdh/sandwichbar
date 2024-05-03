package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.OrderItemDTO;
import be.faros.sandwichbar.entity.OrderItem;

public class OrderItemMapper {

    private final ProductMapper productMapper = new ProductMapper();

    public OrderItemDTO maptoOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getQuantity(),
                productMapper.mapToDTO(orderItem.getProduct())
        );
    }
}
