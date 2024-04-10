package be.faros.sandwichbar.service;

import be.faros.sandwichbar.SandwichbarTestBase;
import be.faros.sandwichbar.dto.UserDTO;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidUserException;
import be.faros.sandwichbar.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static be.faros.sandwichbar.mother.UserMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest extends SandwichbarTestBase {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Register a new user")
    public void registerNewUser() {
        User user = createExistingUserPino();
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO userDTO = userService.registerUser(createNewUserPinoDTO());

        assertEquals(100, userDTO.id());
    }

    @Test
    @DisplayName("Register a new user when already exist")
    public void registerNewUser_emailAlreadyExist() {
        User user = createExistingUserPino();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
            userService.registerUser(createNewUserPinoDTO())
        );

        assertEquals("existing_email", exception.getMessage());
    }

    @Test
    @DisplayName("Register a new user when email is invalid")
    public void registerNewUser_emailInvalid() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                userService.registerUser(createInvalidEmailUserPinoDTO())
        );

        assertEquals("invalid_email", exception.getMessage());
    }

    @Test
    @DisplayName("Register a new user when password is invalid")
    public void registerNewUser_passwordInvalid() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                userService.registerUser(createInvalidPasswordUserPinoDTO())
        );

        assertEquals("invalid_password", exception.getMessage());
    }

    @Test
    @DisplayName("Log in user")
    public void loginUser() {
        User user = createExistingUserPino();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.loginUser(createExistingUserPinoDTO());

        assertEquals(user.getId(), userDTO.id());
    }

    @Test
    @DisplayName("Log in user when user not exists")
    public void loginUser_userNotExist() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                userService.loginUser(createExistingUserPinoDTO())
        );

        assertEquals("user_not_found", exception.getMessage());
    }

    @Test
    @DisplayName("Log in user when password is different")
    public void loginUser_passwordIsDifferent() {
        User user = createExistingUserPino();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                userService.loginUser(createExistingUserPinoDifferentPasswordDTO())
        );

        assertEquals("different_password", exception.getMessage());
    }
}