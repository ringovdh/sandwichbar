package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.OrderRequest;
import be.faros.sandwichbar.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderController {

    ResponseEntity<OrderResponse> createOrder(OrderRequest order);
}
