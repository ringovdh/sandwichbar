package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.response.GetDrinksResponse;
import be.faros.sandwichbar.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/drinks")
public class DrinkControllerImpl implements DrinkController {

    private final DrinkService drinkService;

    @Autowired
    public DrinkControllerImpl(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    @Override
    public ResponseEntity<GetDrinksResponse> getDrinks() {
        return ResponseEntity.ok().body(drinkService.findAllAvailableDrinks());
    }

}
