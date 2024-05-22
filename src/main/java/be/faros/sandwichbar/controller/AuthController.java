package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.UserinfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface AuthController {

    ResponseEntity<UserinfoDTO> getUserInfo(OidcUser oidcUser);
}
