package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.request.OrderRequest;
import be.faros.sandwichbar.dto.response.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest order);
}
