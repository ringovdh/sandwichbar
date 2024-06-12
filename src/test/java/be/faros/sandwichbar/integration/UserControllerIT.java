package be.faros.sandwichbar.integration;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIT extends ControllerITBase {

    @Autowired
    private UserRepository userRepository;

    private final String GET_USERINFO_URL = "/userInfo";
    private final String UPDATE_USERINFO_URL = "/userInfo";

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(1, 'User from Sandwichbar', 'User', 'user@sandwich.be', 'user@sandwich.be');
            """)
    @DisplayName("Get user info")
    public void getUserInfo() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get(GET_USERINFO_URL)
                        .with(oidcLogin().oidcUser(user)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserinfoDTO response = objectMapper.readValue(result, UserinfoDTO.class);
        assertEquals("user@sandwich.be", response.userRef());
        assertEquals("User", response.username());
    }

    @Test
    @Sql(statements = """
            INSERT INTO "user"(id, name, username, email, user_ref) VALUES(2, 'User from Sandwichbar', 'User', 'user@sandwich.be', 'user@sandwich.be');
            """)
    @DisplayName("Update user info")
    @Transactional
    public void updateUserInfo() throws Exception {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("My username");
        String valueAsJson = objectMapper.writeValueAsString(updateUserRequest);

        mvc.perform(MockMvcRequestBuilders.put(UPDATE_USERINFO_URL)
                        .with(oidcLogin().oidcUser(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsJson))
                .andExpect(status().isOk());

        Optional<User> savedUser = userRepository.findByUserRef(user.getEmail());

        assertTrue(savedUser.isPresent());
        assertEquals("My username", savedUser.get().getUsername());
    }
}