package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import be.faros.sandwichbar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @PostMapping
    @Override
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest order) {
        return ResponseEntity.ok().body(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable int id) {
        return ResponseEntity.ok().body(orderService.findById(id));
    }

    @GetMapping("/users/{id}")
    @Override
    public ResponseEntity<GetOrdersResponse> getOrdersByUser(
            @PathVariable int id) {
        return ResponseEntity.ok().body(orderService.findByUserId(id));
    }

}
