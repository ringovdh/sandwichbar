package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.OrderItemDTO;
import be.faros.sandwichbar.dto.request.OrderRequest;
import be.faros.sandwichbar.dto.response.OrderResponse;
import be.faros.sandwichbar.entity.*;
import be.faros.sandwichbar.exception.InvalidOrderException;
import be.faros.sandwichbar.repository.DrinkRepository;
import be.faros.sandwichbar.repository.OrderRepository;
import be.faros.sandwichbar.repository.SandwichRepository;
import be.faros.sandwichbar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SandwichRepository sandwichRepository;
    private final DrinkRepository drinkRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, SandwichRepository sandwichRepository, DrinkRepository drinkRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.sandwichRepository = sandwichRepository;
        this.drinkRepository = drinkRepository;
    }


    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.userId()).orElseThrow(() -> new InvalidOrderException("unknown_user"));
        createNewOrder(user, orderRequest);
        return null;
    }

    private void createNewOrder(User user, OrderRequest orderRequest) {
        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);
        /*order.setItems(createOrderItems(order, orderRequest.items()));
        order.setPrice(calculatePrice(order.getItems()));*/
        orderRepository.save(order);
    }

    private double calculatePrice(List<OrderItem> items) {
        return 11.0;
    }

    private List<OrderItem> createOrderItems(Order order, List<OrderItemDTO> items) {
        List<OrderItem> orderItems = new ArrayList<>();
        items.forEach(i -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(i.quantity());
            if (i.sandwich() != null) {
                Sandwich sandwich = sandwichRepository.findById(i.sandwich().id()).orElseThrow(() -> new InvalidOrderException("unknown_sandwich"));
                orderItem.setSandwich(sandwich);
                orderItem.setPrice(i.quantity() * sandwich.getPrice());
            }
            if (i.drink() != null) {
                Drink drink = drinkRepository.findById(i.drink().id()).orElseThrow(() -> new InvalidOrderException("unknown_drink"));
                if (drink.getStock() > i.quantity()) {
                    orderItem.setDrink(drink);
                    orderItem.setPrice(i.quantity() * drink.getPrice());
                    drink.setStock(drink.getStock() - i.quantity());
                    drinkRepository.save(drink);
                }
            }
            orderItems.add(orderItem);
        });

        return orderItems;
    }
}
