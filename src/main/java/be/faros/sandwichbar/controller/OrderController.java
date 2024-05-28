package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface OrderController {

    ResponseEntity<CreateOrderResponse> createOrder(CreateOrderRequest order, OidcUser principal);

    ResponseEntity<GetOrderResponse> getOrder(int id, OidcUser principal);

    ResponseEntity<GetOrdersResponse> getOrdersByUser(OidcUser principal);
}
