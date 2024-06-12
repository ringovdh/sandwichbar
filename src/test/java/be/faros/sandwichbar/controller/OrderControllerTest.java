package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.CreateOrderRequest;
import be.faros.sandwichbar.dto.response.CreateOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrderResponse;
import be.faros.sandwichbar.dto.response.GetOrdersResponse;
import be.faros.sandwichbar.service.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends ControllerTestBase {

    @MockBean
    private OrderServiceImpl orderService;

    private final String GET_ORDERS_URL = "/orders/users";
    private final String GET_ALL_ORDERS_URL = "/orders";
    private final String GET_ORDER_URL = "/orders/";
    private final String CREATE_ORDER_URL = "/orders";

    @Test
    @DisplayName("Create order as user")
    void createOrder_asUser_succes() throws Exception {
        CreateOrderRequest order = new CreateOrderRequest( emptyList(), null);
        String valueAsJson = objectMapper.writeValueAsString(order);
        when(orderService.createOrder(eq(order), eq(user.getName()))).thenReturn(new CreateOrderResponse(1));

        mvc.perform(MockMvcRequestBuilders.post(CREATE_ORDER_URL, order)
                        .with(oidcLogin().oidcUser(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsJson))
                .andExpect(status().isOk());

        verify(orderService).createOrder(eq(order), eq(user.getName()));
    }

    @Test
    @DisplayName("Create order without authentication")
    void createOrder_accessDenied() throws Exception {
        CreateOrderRequest order = new CreateOrderRequest( emptyList(), null);
        String valueAsJson = objectMapper.writeValueAsString(order);

        mvc.perform(MockMvcRequestBuilders.post(CREATE_ORDER_URL, order)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsJson))
                .andExpect(status().is3xxRedirection());

        verify(orderService, never()).createOrder(eq(order), eq(user.getName()));
    }

    @Test
    @DisplayName("Create order as admin")
    void createOrder_asAdmin() throws Exception {
        CreateOrderRequest order = new CreateOrderRequest( emptyList(), null);
        String valueAsJson = objectMapper.writeValueAsString(order);

        mvc.perform(MockMvcRequestBuilders.post(CREATE_ORDER_URL, order)
                        .with(oidcLogin().oidcUser(admin))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsJson))
                .andExpect(status().isForbidden());

        verify(orderService, never()).createOrder(eq(order), eq(user.getName()));
    }

    @Test
    @DisplayName("Get order as user")
    void getOrderById_asUser_succes() throws Exception {
        int orderId = 1;
        when(orderService.findById(orderId)).thenReturn(new GetOrderResponse(orderId, emptyList(), 0.0, ""));

        mvc.perform(MockMvcRequestBuilders.get(GET_ORDER_URL + orderId)
                        .with(oidcLogin().oidcUser(user))
                )
                .andExpect(status().isOk());

        verify(orderService).findById(orderId);
    }

    @Test
    @DisplayName("Get order as admin")
    void getOrderById_asAdmin_succes() throws Exception {
        int orderId = 1;
        when(orderService.findById(orderId)).thenReturn(new GetOrderResponse(orderId, emptyList(), 0.0, ""));

        mvc.perform(MockMvcRequestBuilders.get(GET_ORDER_URL + orderId)
                        .with(oidcLogin().oidcUser(admin))
                )
                .andExpect(status().isOk());

        verify(orderService).findById(orderId);
    }

    @Test
    @DisplayName("Get order without authentication")
    void getOrderById_accessDenied() throws Exception {
        int orderId = 1;
        mvc.perform(MockMvcRequestBuilders.get(GET_ORDER_URL + orderId))
                .andExpect(status().is3xxRedirection());

        verify(orderService, never()).findById(orderId);
    }

    @Test
    @DisplayName("Get all orders for user")
    void getOrdersByUser_succes() throws Exception {
        when(orderService.findByUser(user.getName())).thenReturn(new GetOrdersResponse(emptyList()));

        mvc.perform(MockMvcRequestBuilders.get(GET_ORDERS_URL)
                        .with(oidcLogin().oidcUser(user))
                )
                .andExpect(status().isOk());

        verify(orderService).findByUser(user.getName());
    }

    @Test
    @DisplayName("Get all orders for user without authentication")
    void getOrdersByUser_accessDenied() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_ORDERS_URL))
                .andExpect(status().is3xxRedirection());

        verify(orderService, never()).findByUser(user.getName());
    }

    @Test
    @DisplayName("Get all orders for user with admin role")
    void getOrdersByUser_wrongRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_ORDERS_URL)
                        .with(oidcLogin().oidcUser(admin))
                )
                .andExpect(status().isForbidden());

        verify(orderService, never()).findByUser(user.getName());
    }

    @Test
    @DisplayName("Get all orders as admin")
    void getAllOrders_succes() throws Exception {
        when(orderService.getAllOrders()).thenReturn(new GetOrdersResponse(emptyList()));

        mvc.perform(MockMvcRequestBuilders.get(GET_ALL_ORDERS_URL)
                        .with(oidcLogin().oidcUser(admin))
                )
                .andExpect(status().isOk());

        verify(orderService).getAllOrders();
    }

    @Test
    @DisplayName("Get all orders without authentication")
    void getAllOrders_accessDenied() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_ALL_ORDERS_URL))
                .andExpect(status().is3xxRedirection());

        verify(orderService, never()).getAllOrders();
    }

    @Test
    @DisplayName("Get all orders with user role")
    void getAllOrders_wrongRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(GET_ALL_ORDERS_URL)
                        .with(oidcLogin().oidcUser(user)))
                .andExpect(status().isForbidden());

        verify(orderService, never()).getAllOrders();
    }

}
