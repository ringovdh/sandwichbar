package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.DrinkDTO;
import be.faros.sandwichbar.dto.response.GetDrinksResponse;
import be.faros.sandwichbar.entity.Drink;
import be.faros.sandwichbar.mapper.DrinkMapper;
import be.faros.sandwichbar.repository.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;

    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
        this.drinkMapper = new DrinkMapper();
    }


    @Transactional(readOnly = true)
    @Override
    public GetDrinksResponse findAllAvailableDrinks() {
        List<Drink> drinks = drinkRepository.findAll();
        List<DrinkDTO> filteredDrinks = drinks.stream()
                .filter(d -> d.getStock() != 0)
                .map(drinkMapper::mapToDTO)
                .toList();
        return new GetDrinksResponse(filteredDrinks);
    }
}
