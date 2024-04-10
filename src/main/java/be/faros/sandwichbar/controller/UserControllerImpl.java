package be.faros.sandwichbar.controller;


import be.faros.sandwichbar.dto.UserDTO;
import be.faros.sandwichbar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Override
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(userService.registerUser(user));
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }
}
