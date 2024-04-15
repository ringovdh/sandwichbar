package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.request.LoginRequest;
import be.faros.sandwichbar.dto.request.RegisterRequest;
import be.faros.sandwichbar.dto.response.LoginResponse;

public interface AuthenticationService {

    void registerUser(RegisterRequest registerRequest);

    LoginResponse loginUser(LoginRequest loginRequest);
}
