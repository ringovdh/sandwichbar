package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.DrinkDTO;
import be.faros.sandwichbar.dto.OrderItemDTO;
import be.faros.sandwichbar.dto.SandwichDTO;
import be.faros.sandwichbar.dto.request.OrderRequest;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.entity.OrderItem;
import be.faros.sandwichbar.entity.Sandwich;
import be.faros.sandwichbar.entity.User;

import java.util.List;

public class OrderMother {

    public static OrderRequest createNewOrderRequest(int userId, SandwichDTO sandwich, DrinkDTO drink) {
        return new OrderRequest(
                userId,
                List.of(createNewOrderItem_sandwich_DTO(sandwich), createNewOrderItem_drink_DTO(drink)));
    }

    private static OrderItemDTO createNewOrderItem_sandwich_DTO(SandwichDTO sandwich) {
        return new OrderItemDTO(0,
                1,
                sandwich,
                null);
    }

    private static OrderItemDTO createNewOrderItem_drink_DTO(DrinkDTO drink) {
        return new OrderItemDTO(0,
                1,
                null,
                drink);
    }

    private static Order getOrder(User user, List<OrderItem> items) {
        Order order = new Order();
        order.setUser(user);
        //order.setItems(items);
        return order;
    }

    public static OrderItem createNewOrderItem_sandwich(Sandwich sandwich) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(1);
        orderItem.setSandwich(sandwich);
        orderItem.setPrice(4.5);
        return orderItem;
    }

}
