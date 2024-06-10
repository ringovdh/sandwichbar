package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.request.CreateOrderItemDTO;
import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.entity.Ingredient;
import be.faros.sandwichbar.entity.Order;
import be.faros.sandwichbar.entity.OrderItem;
import be.faros.sandwichbar.entity.Product;
import be.faros.sandwichbar.entity.Sandwich;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidOrderException;
import be.faros.sandwichbar.mapper.OrderMapper;
import be.faros.sandwichbar.repository.IngredientRepository;
import be.faros.sandwichbar.repository.OrderRepository;
import be.faros.sandwichbar.repository.ProductRepository;
import be.faros.sandwichbar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            IngredientRepository ingredientRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = new OrderMapper();
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String ref) {
        User user = userRepository.findByUserRef(ref)
                .orElseThrow(() -> new InvalidOrderException("unknown_user"));
        Order newOrder = createNewOrder(user, createOrderRequest);
        return new CreateOrderResponse(newOrder.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public GetOrderResponse findById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new InvalidOrderException("unknown_order"));
        return orderMapper.mapToGetOrderResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public GetOrdersResponse findByUser(String ref) {
        List<Order> orders = orderRepository.findByUser_userRef(ref);
        return new GetOrdersResponse(orders.stream()
                .map(orderMapper::mapToGetOrderResponse).toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GetOrdersResponse getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return new GetOrdersResponse(orders.stream()
                .map(orderMapper::mapToGetOrderResponse).toList());
    }

    private Order createNewOrder(User user, CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setUser(user);
        order.setItems(createOrderItems(order, createOrderRequest.items()));
        return orderRepository.save(order);
    }

    private List<OrderItem> createOrderItems(Order order, List<CreateOrderItemDTO> items) {
        List<OrderItem> orderItems = new ArrayList<>();
        items.forEach(i -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(i.quantity());

            Product product = productRepository.findById(i.productId()).orElseThrow(() -> new InvalidOrderException("unknown_product"));
            orderItem.setProduct(product);

            if (product.getProductType().equals("SANDWICH")) {
                Sandwich sandwich = (Sandwich) product;
                updateStock(sandwich.getIngredients(), i.quantity());
            } else {
                Drink drink = (Drink) product;
                if (drink.getStock() > i.quantity()) {
                    drink.setStock(drink.getStock() - i.quantity());
                    productRepository.save(drink);
                } else {
                    throw new InvalidOrderException("out_of_stock_drink");
                }
            }
            orderItems.add(orderItem);
        });

        return orderItems;
    }

    private void updateStock(List<Ingredient> ingredients, int quantity) {
        ingredients.forEach(ingr -> {
            Ingredient ingredient = ingredientRepository.findById(ingr.getId())
                    .orElseThrow(() -> new InvalidOrderException("unknown_ingredient"));
            if (ingredient.getStock() - quantity < 0) {
                throw new InvalidOrderException("out_of_stock_ingredient");
            }
            ingredient.setStock(ingredient.getStock() - quantity);
            ingredientRepository.save(ingredient);
        });
    }
}
