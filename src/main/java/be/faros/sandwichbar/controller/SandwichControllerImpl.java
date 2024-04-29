package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.response.GetSandwichesResponse;
import be.faros.sandwichbar.service.SandwichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sandwiches")
public class SandwichControllerImpl implements SandwichController {

    private final SandwichService sandwichService;

    @Autowired
    public SandwichControllerImpl(SandwichService sandwichService) {
        this.sandwichService = sandwichService;
    }


    @GetMapping
    @Override
    public ResponseEntity<GetSandwichesResponse> getSandwiches() {
        return ResponseEntity.ok().body(sandwichService.findAllAvailableSandwiches());
    }

}
