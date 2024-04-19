package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.OrderItemDTO;
import be.faros.sandwichbar.entity.OrderItem;

public class OrderItemMapper {

    private final SandwichMapper sandwichMapper = new SandwichMapper();
    private final DrinkMapper drinkMapper = new DrinkMapper();

    public OrderItemDTO maptoOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getSandwich() != null ? sandwichMapper.mapToDTO(orderItem.getSandwich()) : null,
                orderItem.getDrink() != null ? drinkMapper.mapToDTO(orderItem.getDrink()) : null
        );
    }
}
