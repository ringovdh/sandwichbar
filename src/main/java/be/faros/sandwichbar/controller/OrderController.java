package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderController {

    ResponseEntity<CreateOrderResponse> createOrder(CreateOrderRequest order);

    ResponseEntity<GetOrderResponse> getOrder(int id);
}
