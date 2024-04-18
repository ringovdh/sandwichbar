package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.OrderRequest;
import be.faros.sandwichbar.dto.response.OrderResponse;
import be.faros.sandwichbar.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest order) {
        return ResponseEntity.ok().body(orderService.createOrder(order));
    }

}
