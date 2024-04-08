package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.OrderDTO;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
   // private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderDTO createOrder(OrderDTO order) {
        createNewOrder(order);
        return null;
    }

    private void createNewOrder(OrderDTO orderDTO) {
        Order order = new Order();
//        order.setUser(orderDTO.user());
//        order.setItems(createOrderItems(order));
    }
}
