package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.ProductDTO;
import be.faros.sandwichbar.dto.request.CreateOrderItemDTO;
import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import be.faros.sandwichbar.entity.Ingredient;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.entity.Sandwich;
import be.faros.sandwichbar.repository.IngredientRepository;
import be.faros.sandwichbar.repository.OrderRepository;
import be.faros.sandwichbar.repository.SandwichRepository;
import be.faros.sandwichbar.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static be.faros.sandwichbar.mother.AddressMother.createAddressDTO;
import static be.faros.sandwichbar.mother.IngredientMother.getCheddar;
import static be.faros.sandwichbar.mother.IngredientMother.getTomatoes;
import static be.faros.sandwichbar.mother.OrderMother.createNewOrderItem_sandwich;
import static be.faros.sandwichbar.mother.OrderMother.createOrder;
import static be.faros.sandwichbar.mother.SandwichMother.getCheeseSandwich;
import static be.faros.sandwichbar.mother.UserMother.createExistingUserPino;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest extends SandwichbarTestBase {

    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    SandwichRepository sandwichRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @InjectMocks
    OrderServiceImpl orderService;


    @Test
    @DisplayName("Find an order by id")
    public void findOrderById() {
        int orderId = 10;
        Order order = createOrder(createExistingUserPino(), List.of(createNewOrderItem_sandwich(getCheeseSandwich())));
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        GetOrderResponse result = orderService.findById(orderId);

        verify(orderRepository).findById(orderId);
        assertEquals(orderId, result.id());
    }

    @Test
    @DisplayName("Find all orders by userId")
    public void findByUserId() {
        int userId = 2;
        Order order1 = createOrder(createExistingUserPino(), List.of(createNewOrderItem_sandwich(getCheeseSandwich())));
        Order order2 = createOrder(createExistingUserPino(), List.of(createNewOrderItem_sandwich(getCheeseSandwich())));

        when(orderRepository.findByUserId(userId)).thenReturn(List.of(order1, order2));

        GetOrdersResponse result = orderService.findByUserId(userId);

        verify(orderRepository).findByUserId(userId);
        assertEquals(2, result.orders().size());
    }

    @Test
    @DisplayName("Create new order")
    public void createNewOrder() {
        String productId = "123";
        Sandwich sandwich = getCheeseSandwich();
        Ingredient tomato = getTomatoes();
        Ingredient cheddar = getCheddar();

        Order newOrder = createOrder(createExistingUserPino(), List.of(createNewOrderItem_sandwich(getCheeseSandwich())));

        CreateOrderRequest request = new CreateOrderRequest(
                2,
                List.of(new CreateOrderItemDTO(1, new ProductDTO(productId))),
                createAddressDTO()
        );

        when(userRepository.findById(request.userId())).thenReturn(Optional.of(createExistingUserPino()));
        when(sandwichRepository.findByProductId(productId)).thenReturn(Optional.of(sandwich));
        when(ingredientRepository.findById(tomato.getId())).thenReturn(Optional.of(tomato));
        when(ingredientRepository.findById(cheddar.getId())).thenReturn(Optional.of(cheddar));
        when(orderRepository.save(any(Order.class))).thenReturn(newOrder);

        CreateOrderResponse result = orderService.createOrder(request);

        verify(userRepository).findById(request.userId());
        verify(sandwichRepository).findByProductId(productId);
        verify(ingredientRepository).findById(tomato.getId());
        verify(ingredientRepository).findById(cheddar.getId());
        verify(orderRepository).save(any(Order.class));

        assertEquals(newOrder.getId(), result.id());
    }

}