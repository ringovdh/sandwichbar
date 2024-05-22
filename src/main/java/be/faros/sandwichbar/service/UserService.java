package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserinfoDTO;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface UserService {

    UserinfoDTO getUserInfo(OidcUser oidcUser);
}
