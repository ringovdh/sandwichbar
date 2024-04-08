package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderController {

    ResponseEntity<OrderDTO> createOrder(OrderDTO order);
}
