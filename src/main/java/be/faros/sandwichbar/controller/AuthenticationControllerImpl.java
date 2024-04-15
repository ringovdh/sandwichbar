package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.LoginRequest;
import be.faros.sandwichbar.dto.request.RegisterRequest;
import be.faros.sandwichbar.dto.response.LoginResponse;
import be.faros.sandwichbar.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/authenticate")
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationControllerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    @Override
    public ResponseEntity<Void> registerUser(@RequestBody RegisterRequest registerRequest) {
        authenticationService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.loginUser(loginRequest));
    }
}
