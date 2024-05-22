package be.faros.sandwichbar.mother;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collections;

public class OidcUserMother {

    public static OidcUser createOidcUser() {
        return  new DefaultOidcUser(
                Collections.emptyList(),
                OidcIdToken.withTokenValue("id-token")
                        .claim("user_name", "")
                        .claim("username", "oauth0|1234")
                        .build(),
                "user_name");
    }
}
