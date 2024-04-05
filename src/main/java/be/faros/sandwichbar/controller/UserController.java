package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<UserDTO> registerUser(UserDTO user);
}
