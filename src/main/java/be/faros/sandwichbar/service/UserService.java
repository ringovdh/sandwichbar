package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserDTO;

public interface UserService {

    UserDTO registerUser(UserDTO user);

    UserDTO loginUser(UserDTO user);
}
