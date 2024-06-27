package be.faros.sandwichbar.mother;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class OidcUserMother {

    public static OidcUser createOidcUser_userRole() {
        return  new DefaultOidcUser(
                AuthorityUtils.createAuthorityList("ROLE_USER"),
                OidcIdToken.withTokenValue("id-token").claim("email", "user@sandwich.be").build(),
                "email");
    }

    public static OidcUser createOidcUser_adminRole() {
        return  new DefaultOidcUser(
                AuthorityUtils.createAuthorityList("ROLE_ADMIN"),
                OidcIdToken.withTokenValue("id-token").claim("email", "admin@sandwich.be").build(),
                "email");
    }
}
