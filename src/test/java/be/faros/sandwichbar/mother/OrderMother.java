package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.request.CreateOrderItemDTO;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.entity.OrderItem;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.User;

import java.util.List;

public class OrderMother {

    public static Order createOrder(User user, List<OrderItem> items) {
        Order order = new Order();
        order.setId(10);
        order.setUser(user);
        order.setItems(items);
        return order;
    }

    public static OrderItem createNewOrderItem(Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(1);
        orderItem.setProductRef(product.getProductRef());
        return orderItem;
    }

    public static CreateOrderItemDTO createNewCreateOrderItemDTO(Product product) {
        return new CreateOrderItemDTO(1, product);
    }

}
