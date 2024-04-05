package be.faros.sandwichbar.mapper;

import be.faros.sandwichbar.dto.UserDTO;
import be.faros.sandwichbar.entity.User;

public class UserMapper {

    public User mapUserDTOToUser(UserDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        return user;
    }

    public UserDTO mapUserToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
