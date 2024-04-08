package be.faros.sandwichbar.service;

import be.faros.sandwichbar.SandwichbarTestBase;
import be.faros.sandwichbar.repository.OrderRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OrderServiceImplTest extends SandwichbarTestBase {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;
}