package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.response.GetSandwichesResponse;
import be.faros.sandwichbar.entity.Sandwich;
import be.faros.sandwichbar.mapper.SandwichMapper;
import be.faros.sandwichbar.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SandwichServiceImpl implements SandwichService {

    private final SandwichRepository sandwichRepository;
    private final SandwichMapper sandwichMapper;

    @Autowired
    public SandwichServiceImpl(SandwichRepository sandwichRepository) {
        this.sandwichRepository = sandwichRepository;
        this.sandwichMapper = new SandwichMapper();
    }


    @Override
    public GetSandwichesResponse findAllSandwiches() {
        List<Sandwich> sandwiches = sandwichRepository.findAllByIngredientsStockIsNotNull();
        return new GetSandwichesResponse(sandwiches.stream()
                .map(sandwichMapper::mapToDTO).toList());
    }
}
