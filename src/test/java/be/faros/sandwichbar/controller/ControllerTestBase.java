package be.faros.sandwichbar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.test.web.servlet.MockMvc;

import static be.faros.sandwichbar.mother.OidcUserMother.createOidcUser_adminRole;
import static be.faros.sandwichbar.mother.OidcUserMother.createOidcUser_userRole;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class ControllerTestBase {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected final OidcUser admin = createOidcUser_adminRole();
    protected final OidcUser user = createOidcUser_userRole();

}
