package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.request.LoginRequest;
import be.faros.sandwichbar.dto.request.RegisterRequest;
import be.faros.sandwichbar.dto.response.LoginResponse;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidOrderException;
import be.faros.sandwichbar.exception.InvalidUserException;
import be.faros.sandwichbar.repository.UserRepository;
import be.faros.sandwichbar.security.JwtHelper;
import be.faros.sandwichbar.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        validateRegisterRequest(registerRequest);
        createNewUser(registerRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse loginUser(LoginRequest userDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));
        String token = JwtHelper.generateToken(userDTO.email());
        User user = userRepository.findByEmail(userDTO.email()).orElseThrow(() -> new InvalidOrderException("unknown_user"));
        return new LoginResponse(user.getId(), user.getName(), userDTO.email(), token);
    }

    private void createNewUser(RegisterRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (request.email() == null || !Utils.validateEmail(request.email())) {
            throw new InvalidUserException("invalid_email");
        }

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new InvalidUserException("existing_email");
        }

        if (request.password() == null || !Utils.validatePassword(request.password())) {
            throw new InvalidUserException("invalid_password");
        }
    }
}
