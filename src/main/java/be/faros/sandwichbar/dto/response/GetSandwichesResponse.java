package be.faros.sandwichbar.dto.response;

import be.faros.sandwichbar.dto.SandwichDTO;

import java.util.List;

public record GetSandwichesResponse(
        List<SandwichDTO> sandwiches) { }
