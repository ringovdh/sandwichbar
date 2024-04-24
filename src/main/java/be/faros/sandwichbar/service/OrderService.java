package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest order);

    GetOrderResponse findById(int id);

    GetOrdersResponse findByUserId(int id);
}
