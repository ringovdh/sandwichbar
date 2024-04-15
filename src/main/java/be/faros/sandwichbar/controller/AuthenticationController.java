package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.request.LoginRequest;
import be.faros.sandwichbar.dto.request.RegisterRequest;
import be.faros.sandwichbar.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationController {

    ResponseEntity<Void> registerUser(RegisterRequest user);

    ResponseEntity<LoginResponse> loginUser(LoginRequest user);
}
