package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.response.GetSandwichesResponse;

public interface SandwichService {

    GetSandwichesResponse findAllSandwiches();
}
