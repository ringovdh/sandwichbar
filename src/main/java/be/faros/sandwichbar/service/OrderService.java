package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.OrderDTO;

public interface OrderService {

    OrderDTO createOrder(OrderDTO order);
}
