package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.request.RegisterRequest;
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

import static be.faros.sandwichbar.mother.UserMother.createExistingUserPino;
import static be.faros.sandwichbar.mother.UserMother.createInvalidEmailUserRegisterRequest;
import static be.faros.sandwichbar.mother.UserMother.createInvalidPasswordRegisterRequestPino;
import static be.faros.sandwichbar.mother.UserMother.createLoginRequestDifferentPasswordPino;
import static be.faros.sandwichbar.mother.UserMother.createLoginRequestPino;
import static be.faros.sandwichbar.mother.UserMother.createRegisterRequestPino;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest extends SandwichbarTestBase {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    @DisplayName("Register a new user")
    public void registerNewUser() {
        RegisterRequest registerRequest = createRegisterRequestPino();
        authenticationService.registerUser(registerRequest);

        verify(userRepository).findByEmail(registerRequest.email());
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Register a new user when already exist")
    public void registerNewUser_emailAlreadyExist() {
        User user = createExistingUserPino();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
            authenticationService.registerUser(createRegisterRequestPino())
        );

        assertEquals("existing_email", exception.getMessage());
    }

    @Test
    @DisplayName("Register a new user when email is invalid")
    public void registerNewUser_emailInvalid() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                authenticationService.registerUser(createInvalidEmailUserRegisterRequest())
        );

        assertEquals("invalid_email", exception.getMessage());
    }

    @Test
    @DisplayName("Register a new user when password is invalid")
    public void registerNewUser_passwordInvalid() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                authenticationService.registerUser(createInvalidPasswordRegisterRequestPino())
        );

        assertEquals("invalid_password", exception.getMessage());
    }

    @Test
    @DisplayName("Log in user")
    public void loginUser() {
        User user = createExistingUserPino();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        LoginResponse response = authenticationService.loginUser(createLoginRequestPino());

        assertNotNull(response.token());
        assertEquals(user.getEmail(), response.email());
    }

    @Test
    @DisplayName("Log in user when user not exists")
    public void loginUser_userNotExist() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(UsernameNotFoundException.class);
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () ->
                authenticationService.loginUser(createLoginRequestPino())
        );

        assertNotNull(exception);
    }

    @Test
    @DisplayName("Log in user when password is different")
    public void loginUser_passwordIsDifferent() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(BadCredentialsException.class);

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () ->
                authenticationService.loginUser(createLoginRequestDifferentPasswordPino())
        );

        assertNotNull(exception);
    }
}