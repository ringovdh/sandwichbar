package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.request.CreateOrderItemDTO;
import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.entity.OrderItem;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.User;

import java.util.List;

import static be.faros.sandwichbar.mother.AddressMother.createAddressDTO;

public class OrderMother {

    public static CreateOrderRequest createNewOrderRequest(int userId, Product product1, Product product2) {
        return new CreateOrderRequest(
                userId,
                List.of(createNewOrderItem_product_DTO(product1), createNewOrderItem_product_DTO(product2)),
                createAddressDTO());
    }

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
        orderItem.setProduct(product);
        return orderItem;
    }

    private static CreateOrderItemDTO createNewOrderItem_product_DTO(Product product) {
        return new CreateOrderItemDTO(1, product.getId());
    }

}
