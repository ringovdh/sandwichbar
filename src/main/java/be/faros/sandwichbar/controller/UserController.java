package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface UserController {

    ResponseEntity<UserinfoDTO> getUserInfo(OidcUser oidcUser);

    void updateUserInfo(OidcUser oidcUser, UpdateUserRequest updateUserRequest);
}
