package be.faros.sandwichbar.dto.response;

import java.util.List;

public record GetOrdersResponse(
        List<GetOrderResponse> orders) { }
