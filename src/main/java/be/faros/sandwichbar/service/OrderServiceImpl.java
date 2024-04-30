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
import be.faros.sandwichbar.entity.Sandwich;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidOrderException;
import be.faros.sandwichbar.mapper.OrderMapper;
import be.faros.sandwichbar.repository.DrinkRepository;
import be.faros.sandwichbar.repository.IngredientRepository;
import be.faros.sandwichbar.repository.OrderRepository;
import be.faros.sandwichbar.repository.SandwichRepository;
import be.faros.sandwichbar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SandwichRepository sandwichRepository;
    private final DrinkRepository drinkRepository;
    private final OrderMapper orderMapper;
    private final IngredientRepository ingredientRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            SandwichRepository sandwichRepository,
                            DrinkRepository drinkRepository, IngredientRepository ingredientRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.sandwichRepository = sandwichRepository;
        this.drinkRepository = drinkRepository;
        this.orderMapper = new OrderMapper();
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        User user = userRepository.findById(createOrderRequest.userId())
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
    public GetOrdersResponse findByUserId(int id) {
        List<Order> orders = orderRepository.findByUserId(id);
        return new GetOrdersResponse(orders.stream()
                .map(orderMapper::mapToGetOrderResponse).toList());
    }

    private Order createNewOrder(User user, CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setUser(user);
        order.setItems(createOrderItems(order, createOrderRequest.items()));
        order.setPrice(calculatePrice(order.getItems()));
        return orderRepository.save(order);
    }

    private double calculatePrice(List<OrderItem> items) {
        return items.stream()
                .map(o -> {
                    return o.getQuantity() * (o.getSandwich() != null ?
                            o.getSandwich().getPrice() : o.getDrink().getPrice());
                })
                .reduce(0D, Double::sum);
    }

    private List<OrderItem> createOrderItems(Order order, List<CreateOrderItemDTO> items) {
        List<OrderItem> orderItems = new ArrayList<>();
        items.forEach(i -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(i.quantity());

            Optional<Sandwich> sandwich = sandwichRepository.findByProductId(i.product().productId());
            if (sandwich.isPresent()) {
                orderItem.setSandwich(sandwich.get());
                orderItem.setPrice(i.quantity() * sandwich.get().getPrice());
                updateStock(sandwich.get().getIngredients(), i.quantity());
            } else {
                Drink drink = drinkRepository.findByProductId(i.product().productId())
                        .orElseThrow(() -> new InvalidOrderException("unknown_product"));
                if (drink.getStock() > i.quantity()) {
                    orderItem.setDrink(drink);
                    orderItem.setPrice(i.quantity() * drink.getPrice());
                    drink.setStock(drink.getStock() - i.quantity());
                    drinkRepository.save(drink);
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
