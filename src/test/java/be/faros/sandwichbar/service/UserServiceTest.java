package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Optional;

import static be.faros.sandwichbar.mother.OidcUserMother.createOidcUser_userRole;
import static be.faros.sandwichbar.mother.UserMother.createExistingUserPino;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest extends SandwichbarTestBase {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    private final User user = createExistingUserPino();
    private final OidcUser oidcUser = createOidcUser_userRole();

    @Test
    @DisplayName("Get userInfo of existing user")
    public void getUserInfo_ofExistingUser() {
        when(userRepository.findByUserRef(oidcUser.getName())).thenReturn(Optional.of(user));

        UserinfoDTO userInfo = userService.getUserInfo(oidcUser);

        verify(userRepository).findByUserRef(oidcUser.getName());
        assertEquals(user.getUsername(), userInfo.username());
    }

    @Test
    @DisplayName("Get userInfo of new user")
    public void getUserInfo_ofNewUser() {
        when(userRepository.findByUserRef(oidcUser.getName())).thenReturn(Optional.empty());

        UserinfoDTO userInfo = userService.getUserInfo(oidcUser);

        verify(userRepository).findByUserRef(oidcUser.getName());
        verify(userRepository).save(any(User.class));

        assertNull(userInfo.username());
    }

    @Test
    @DisplayName("Update user info")
    public void updateUserInfo() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("No more Pino");
        when(userRepository.findByUserRef(oidcUser.getName())).thenReturn(Optional.of(user));

        userService.updateUserInfo(oidcUser, updateUserRequest);

        verify(userRepository).findByUserRef(oidcUser.getName());
        verify(userRepository).save(any(User.class));

        Optional<User> result = userRepository.findByUserRef(oidcUser.getName());

        assertTrue(result.isPresent());
        assertEquals("No more Pino", user.getUsername());
    }
}