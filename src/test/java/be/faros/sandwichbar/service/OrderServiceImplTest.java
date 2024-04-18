package be.faros.sandwichbar.service;

import be.faros.sandwichbar.repository.OrderRepositoryTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OrderServiceImplTest extends SandwichbarTestBase {

    @Mock
    OrderRepositoryTest orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;




}