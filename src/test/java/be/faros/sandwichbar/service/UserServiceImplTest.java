package be.faros.sandwichbar.service;

import be.faros.sandwichbar.SandwichbarTestBase;
import be.faros.sandwichbar.dto.UserDTO;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.exception.InvalidUserException;
import be.faros.sandwichbar.repository.UserRepository;
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
    public void registerNewUser() {
        User user = createExistingUser();
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO userDTO = userService.registerUser(createNewUserPinoDTO());

        assertEquals(100, userDTO.id());
    }

    @Test
    public void registerNewUser_emailAlreadyExist() {
        User user = createExistingUser();
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
            userService.registerUser(createNewUserPinoDTO())
        );

        assertEquals("existing_email", exception.getMessage());
    }

    @Test
    public void registerNewUser_emailInvalid() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                userService.registerUser(createInvalidEmailUserPinoDTO())
        );

        assertEquals("invalid_email", exception.getMessage());
    }

    @Test
    public void registerNewUser_passwordInvalid() {
        InvalidUserException exception = assertThrows(InvalidUserException.class, () ->
                userService.registerUser(createInvalidPasswordUserPinoDTO())
        );

        assertEquals("invalid_password", exception.getMessage());
    }
}