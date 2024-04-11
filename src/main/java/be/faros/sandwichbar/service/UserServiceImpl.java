package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserDTO;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidUserException;
import be.faros.sandwichbar.mapper.UserMapper;
import be.faros.sandwichbar.repository.UserRepository;
import be.faros.sandwichbar.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userMapper = new UserMapper();
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        validateUser(userDTO);
        User user = createNewUser(userDTO);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO loginUser(UserDTO userDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));

        User user = userRepository.findByEmail(userDTO.email())
                .orElseThrow(() -> new InvalidUserException("user_not_found"));
        compareUserPassword(user.getPassword(), userDTO.password());
        return userMapper.mapUserToUserDTO(user);
    }

    private User createNewUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        return userRepository.save(user);
    }

    private void compareUserPassword(String userPassword, String incomingPassword) {
        if (!userPassword.equals(incomingPassword)) {
            throw new InvalidUserException("different_password");
        }
    }

    private void validateUser(UserDTO user) {
        if (user.email() == null || !Utils.validateEmail(user.email())) {
            throw new InvalidUserException("invalid_email");
        }

        if (userRepository.findByEmail(user.email()).isPresent()) {
            throw new InvalidUserException("existing_email");
        }

        if (user.password() == null || !Utils.validatePassword(user.password())) {
            throw new InvalidUserException("invalid_password");
        }
    }
}
