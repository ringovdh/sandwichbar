package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.request.CreateOrderItemDTO;
import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.entity.OrderItem;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidOrderException;
import be.faros.sandwichbar.repository.OrderRepository;
import be.faros.sandwichbar.repository.ProductRepository;
import be.faros.sandwichbar.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static be.faros.sandwichbar.mother.AddressMother.createAddressDTO;
import static be.faros.sandwichbar.mother.OrderMother.createNewOrderItem;
import static be.faros.sandwichbar.mother.OrderMother.createOrder;
import static be.faros.sandwichbar.mother.ProductMother.createImportedProduct;
import static be.faros.sandwichbar.mother.ProductMother.createExistingDrink;
import static be.faros.sandwichbar.mother.ProductMother.createExistingDrinkOutOfStock;
import static be.faros.sandwichbar.mother.UserMother.createExistingUserPino;
import static be.faros.sandwichbar.mother.UserMother.createExistingUserTommy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest extends SandwichbarTestBase {

    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    private final String userRef = "oAuth|1234";
    private final User user = createExistingUserPino();
    private final User user2 = createExistingUserTommy();


    @Test
    @DisplayName("Find an order by id")
    public void findOrderById() {
        Order order = createOrder(user, List.of(createNewOrderItem(createExistingDrink())));

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        GetOrderResponse result = orderService.findById(order.getId());

        verify(orderRepository).findById(order.getId());
        assertEquals(order.getId(), result.id());
        assertEquals(order.getItems().size(), result.items().size());
    }

    @Test
    @DisplayName("Find all orders by user")
    public void findAllOrdersByUser() {
        Order order1 = createOrder(user, List.of(createNewOrderItem(createExistingDrink())));
        Order order2 = createOrder(user, List.of(createNewOrderItem(createExistingDrink())));

        when(orderRepository.findByUser_userRef(userRef)).thenReturn(List.of(order1, order2));

        GetOrdersResponse result = orderService.findByUser(userRef);

        verify(orderRepository).findByUser_userRef(userRef);
        assertEquals(2, result.orders().size());
    }

    @Test
    @DisplayName("Find all orders")
    public void findAllOrders() {
        Order order1 = createOrder(user, List.of(createNewOrderItem(createImportedProduct())));
        Order order2 = createOrder(user2, List.of(createNewOrderItem(createImportedProduct())));

        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));

        GetOrdersResponse result = orderService.getAllOrders();

        verify(orderRepository).findAll();
        assertEquals(2, result.orders().size());
    }

    @Test
    @DisplayName("Create new order")
    public void createNewOrder() {
        Product importedProduct = createImportedProduct();
        OrderItem orderItem1 = createNewOrderItem(importedProduct);
        Product drink = createExistingDrink();
        OrderItem orderItem2 = createNewOrderItem(drink);
        Order savedOrder = new Order();
        savedOrder.setId(10);
        savedOrder.setUser(user);
        savedOrder.setItems(List.of(orderItem1, orderItem2));
        CreateOrderRequest request = new CreateOrderRequest(
                List.of(new CreateOrderItemDTO(1, importedProduct),
                        new CreateOrderItemDTO(1, drink)),
                createAddressDTO()
        );

        when(userRepository.findByUserRef(userRef)).thenReturn(Optional.of(user));
        when(productRepository.findByProductRef(importedProduct.getProductRef())).thenReturn(Optional.empty());
        when(productRepository.findByProductRef(drink.getProductRef())).thenReturn(Optional.of(drink));
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        orderService.createOrder(request, userRef);

        verify(userRepository).findByUserRef(userRef);
        verify(productRepository).findByProductRef(importedProduct.getProductRef());
        verify(productRepository).findByProductRef(drink.getProductRef());
    }

    @Test
    @DisplayName("Create new order when drink is out of stock")
    public void createNewOrder_whenDrinkIsOutOfStock_throwsInvalidOrderException() {
        Product drink = createExistingDrinkOutOfStock();

        CreateOrderRequest request = new CreateOrderRequest(
                List.of(new CreateOrderItemDTO(1, drink)),
                createAddressDTO()
        );

        when(userRepository.findByUserRef(userRef)).thenReturn(Optional.of(user));
        when(productRepository.findByProductRef(drink.getProductRef())).thenReturn(Optional.of(drink));

        InvalidOrderException exception = assertThrows(InvalidOrderException.class, () ->
                orderService.createOrder(request, userRef)
        );
        verify(userRepository).findByUserRef(userRef);
        verify(productRepository).findByProductRef(drink.getProductRef());
        assertEquals("out_of_stock_drink", exception.getMessage());
    }

}