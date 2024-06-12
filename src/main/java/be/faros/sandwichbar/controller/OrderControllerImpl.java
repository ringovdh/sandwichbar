package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import be.faros.sandwichbar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Override
    public ResponseEntity<CreateOrderResponse> createOrder(
            @RequestBody CreateOrderRequest order,
            @AuthenticationPrincipal OidcUser principal) {
        return ResponseEntity.ok().body(orderService.createOrder(order, principal.getName()));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<GetOrderResponse> getOrder(
            @PathVariable int id) {
        return ResponseEntity.ok().body(orderService.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/users")
    @Override
    public ResponseEntity<GetOrdersResponse> getOrdersByUser(
            @AuthenticationPrincipal OidcUser principal) {
        return ResponseEntity.ok().body(orderService.findByUser(principal.getName()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    @Override
    public ResponseEntity<GetOrdersResponse> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

}
