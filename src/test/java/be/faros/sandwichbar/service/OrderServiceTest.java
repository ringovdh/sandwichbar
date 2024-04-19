package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static be.faros.sandwichbar.mother.OrderMother.createNewOrderItem_sandwich;
import static be.faros.sandwichbar.mother.OrderMother.createOrder;
import static be.faros.sandwichbar.mother.SandwichMother.getCheeseSandwich;
import static be.faros.sandwichbar.mother.UserMother.createExistingUserPino;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest extends SandwichbarTestBase {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;


    @Test
    @DisplayName("find an order by id")
    public void findOrderById() {
        int orderId = 10;
        Order order = createOrder(createExistingUserPino(), List.of(createNewOrderItem_sandwich(getCheeseSandwich())));
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        GetOrderResponse result = orderService.findById(10);

        verify(orderRepository).findById(orderId);
        assertEquals(10, result.id());
    }

}