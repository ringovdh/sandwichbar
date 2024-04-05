package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    UserDTO registerUser(UserDTO user);

    List<UserDTO> getUsers();
}
