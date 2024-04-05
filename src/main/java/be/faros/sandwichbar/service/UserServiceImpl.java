package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserDTO;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidUserException;
import be.faros.sandwichbar.mapper.UserMapper;
import be.faros.sandwichbar.repository.UserRepository;
import be.faros.sandwichbar.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }

    @Override
    public List<UserDTO> getUsers() {
        return Collections.singletonList(new UserDTO(1, "TEst", "testmail", "testpassword"));
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.mapUserDTOToUser(userDTO);
        validateUser(user);
        User savedUser = userRepository.save(user);
        return userMapper.mapUserToUserDTO(savedUser);
    }

    private void validateUser(User user) {
        if (user.getEmail() == null || !Utils.validateEmail(user.getEmail())) {
            throw new InvalidUserException("invalid_email");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new InvalidUserException("existing_email");
        }

        if (user.getPassword() == null || !Utils.validatePassword(user.getPassword())) {
            throw new InvalidUserException("invalid_password");
        }
    }
}
