package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface UserService {

    UserinfoDTO getUserInfo(OidcUser oidcUser);

    void updateUser(OidcUser oidcUser, UpdateUserRequest updateUserRequest);
}
