package be.faros.sandwichbar.service;

import be.faros.sandwichbar.SandwichbarTestBase;
import be.faros.sandwichbar.dto.response.LoginResponse;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidUserException;
import be.faros.sandwichbar.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static be.faros.sandwichbar.mother.UserMother.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationServiceImplTest extends SandwichbarTestBase {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthenticationServiceImpl userService;

    @Test
    @DisplayName("Register a new user")
    public void registerNewUser() {
        userService.registerUser(createRegisterRequestPino());
        // What to test??
    }

    @Test
    @DisplayName("Register a new user when already exist")
    public void registerNewUser_emailAlreadyExist() {
        User user = createExistingUserPino();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
            userService.registerUser(createRegisterRequestPino())
        );

        assertEquals("existing_email", exception.getMessage());
    }

    @Test
    @DisplayName("Register a new user when email is invalid")
    public void registerNewUser_emailInvalid() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                userService.registerUser(createInvalidEmailUserRegisterRequest())
        );

        assertEquals("invalid_email", exception.getMessage());
    }

    @Test
    @DisplayName("Register a new user when password is invalid")
    public void registerNewUser_passwordInvalid() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                userService.registerUser(createInvalidPasswordRegisterRequestPino())
        );

        assertEquals("invalid_password", exception.getMessage());
    }

    @Test
    @DisplayName("Log in user")
    public void loginUser() {
        User user = createExistingUserPino();
        LoginResponse response = userService.loginUser(createLoginRequestPino());

        assertNotNull(response.token());
        assertEquals(user.getEmail(), response.email());
    }

    @Test
    @DisplayName("Log in user when user not exists")
    public void loginUser_userNotExist() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(UsernameNotFoundException.class);
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () ->
                userService.loginUser(createLoginRequestPino())
        );

        assertNotNull(exception);
    }

    @Test
    @DisplayName("Log in user when password is different")
    public void loginUser_passwordIsDifferent() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(BadCredentialsException.class);

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () ->
                userService.loginUser(createLoginRequestDifferentPasswordPino())
        );

        assertNotNull(exception);
    }
}