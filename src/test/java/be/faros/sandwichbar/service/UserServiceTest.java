package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Optional;

import static be.faros.sandwichbar.mother.OidcUserMother.createOidcUser;
import static be.faros.sandwichbar.mother.UserMother.createExistingUserPino;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest extends SandwichbarTestBase {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    private final User user = createExistingUserPino();
    
    @Test
    @DisplayName("Get userInfo of existing user")
    public void getUserInfo_ofExistingUser() {
        OidcUser oidcUser = createOidcUser();

        when(userRepository.findByUserRef(oidcUser.getName())).thenReturn(Optional.of(user));

        UserinfoDTO userInfo = userService.getUserInfo(oidcUser);

        verify(userRepository).findByUserRef(oidcUser.getName());
        assertEquals(user.getUsername(), userInfo.username());
    }

    @Test
    @DisplayName("Get userInfo of new user")
    public void getUserInfo_ofNewUser() {
        OidcUser oidcUser = createOidcUser();

        when(userRepository.findByUserRef(oidcUser.getName())).thenReturn(Optional.empty());

        UserinfoDTO userInfo = userService.getUserInfo(oidcUser);

        verify(userRepository).findByUserRef(oidcUser.getName());
        verify(userRepository).save(any(User.class));

        assertNull(userInfo.username());
    }
}