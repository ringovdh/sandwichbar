package be.faros.sandwichbar.mother;

import be.faros.sandwichbar.dto.OrderDTO;
import be.faros.sandwichbar.dto.OrderItemDTO;
import be.faros.sandwichbar.dto.SandwichDTO;
import be.faros.sandwichbar.dto.UserDTO;

import java.util.List;

public class OrderMother {

    public static OrderDTO createNewOrderDTO(UserDTO user) {
        return new OrderDTO(0,
                user,
                List.of(createNewOrderItemDTO()),
                10);
    }

    private static OrderItemDTO createNewOrderItemDTO() {
        return new OrderItemDTO(0,
                1,
                createSandwichDTO(),
                null);
    }

    private static SandwichDTO createSandwichDTO() {
        return new SandwichDTO(
                1,
                "sandwich 1",
                3,
                List.of());
    }
}
