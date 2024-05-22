package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.request.CreateOrderItemDTO;
import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import be.faros.sandwichbar.entity.Ingredient;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.entity.OrderItem;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidOrderException;
import be.faros.sandwichbar.repository.IngredientRepository;
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
import static be.faros.sandwichbar.mother.IngredientMother.getCheddar;
import static be.faros.sandwichbar.mother.IngredientMother.getTomatoes;
import static be.faros.sandwichbar.mother.OrderMother.createNewOrderItem;
import static be.faros.sandwichbar.mother.OrderMother.createOrder;
import static be.faros.sandwichbar.mother.ProductMother.createExistingCheeseSandwich;
import static be.faros.sandwichbar.mother.ProductMother.createExistingDrink;
import static be.faros.sandwichbar.mother.ProductMother.createExistingDrinkOutOfStock;
import static be.faros.sandwichbar.mother.UserMother.createExistingUserPino;
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

    @Mock
    IngredientRepository ingredientRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    private final String userRef = "oAuth|1234";
    private final User user = createExistingUserPino();


    @Test
    @DisplayName("Find an order by id")
    public void findOrderById() {
        Order order = createOrder(user, List.of(createNewOrderItem(createExistingCheeseSandwich())));

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        GetOrderResponse result = orderService.findById(order.getId());

        verify(orderRepository).findById(order.getId());
        assertEquals(order.getId(), result.id());
        assertEquals(order.getItems().size(), result.items().size());
    }

    @Test
    @DisplayName("Find all orders by user")
    public void findByUser() {
        Order order1 = createOrder(user, List.of(createNewOrderItem(createExistingCheeseSandwich())));
        Order order2 = createOrder(user, List.of(createNewOrderItem(createExistingCheeseSandwich())));

        when(orderRepository.findByUser_userRef(userRef)).thenReturn(List.of(order1, order2));

        GetOrdersResponse result = orderService.findByUser(userRef);

        verify(orderRepository).findByUser_userRef(userRef);
        assertEquals(2, result.orders().size());
    }

    @Test
    @DisplayName("Create new order")
    public void createNewOrder() {
        Product sandwich = createExistingCheeseSandwich();
        Ingredient tomato = getTomatoes();
        Ingredient cheddar = getCheddar();
        OrderItem orderItem1 = createNewOrderItem(sandwich);
        Product drink = createExistingDrink();
        OrderItem orderItem2 = createNewOrderItem(drink);
        Order savedOrder = new Order();
        savedOrder.setId(10);
        savedOrder.setUser(user);
        savedOrder.setItems(List.of(orderItem1, orderItem2));
        CreateOrderRequest request = new CreateOrderRequest(
                List.of(new CreateOrderItemDTO(1, sandwich.getId()),
                        new CreateOrderItemDTO(1, drink.getId())),
                createAddressDTO()
        );

        when(userRepository.findByUserRef(userRef)).thenReturn(Optional.of(user));
        when(productRepository.findById(sandwich.getId())).thenReturn(Optional.of(sandwich));
        when(ingredientRepository.findById(tomato.getId())).thenReturn(Optional.of(tomato));
        when(ingredientRepository.findById(cheddar.getId())).thenReturn(Optional.of(cheddar));
        when(productRepository.findById(drink.getId())).thenReturn(Optional.of(drink));
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        orderService.createOrder(request, userRef);

        verify(userRepository).findByUserRef(userRef);
        verify(productRepository).findById(sandwich.getId());
        verify(productRepository).findById(drink.getId());
        verify(ingredientRepository).findById(tomato.getId());
        verify(ingredientRepository).findById(cheddar.getId());
    }

    @Test
    @DisplayName("Create new order when drink is out of stock")
    public void createNewOrder_whenDrinkIsOutOfStock_throwsInvalidOrderException() {
        Product drink = createExistingDrinkOutOfStock();

        CreateOrderRequest request = new CreateOrderRequest(
                List.of(new CreateOrderItemDTO(1, drink.getId())),
                createAddressDTO()
        );

        when(userRepository.findByUserRef(userRef)).thenReturn(Optional.of(user));
        when(productRepository.findById(drink.getId())).thenReturn(Optional.of(drink));

        InvalidOrderException exception = assertThrows(InvalidOrderException.class, () ->
                orderService.createOrder(request, userRef)
        );
        verify(userRepository).findByUserRef(userRef);
        verify(productRepository).findById(drink.getId());
        assertEquals("out_of_stock_drink", exception.getMessage());
    }

    @Test
    @DisplayName("Create new order when ingredient is out of stock")
    public void createNewOrder_whenIngredientIsOutOfStock_throwsInvalidOrderException() {
        Product sandwich = createExistingCheeseSandwich();
        Ingredient tomato = getTomatoes();
        tomato.setStock(0);

        CreateOrderRequest request = new CreateOrderRequest(
                List.of(new CreateOrderItemDTO(1, sandwich.getId())),
                createAddressDTO()
        );

        when(userRepository.findByUserRef(userRef)).thenReturn(Optional.of(user));
        when(productRepository.findById(sandwich.getId())).thenReturn(Optional.of(sandwich));
        when(ingredientRepository.findById(tomato.getId())).thenReturn(Optional.of(tomato));

        InvalidOrderException exception = assertThrows(InvalidOrderException.class, () ->
                orderService.createOrder(request, userRef)
        );

        verify(userRepository).findByUserRef(userRef);
        verify(productRepository).findById(sandwich.getId());
        verify(ingredientRepository).findById(tomato.getId());
        assertEquals("out_of_stock_ingredient", exception.getMessage());
    }
}