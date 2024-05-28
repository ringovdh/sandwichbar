package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {

    private UserService userService;

    public AuthControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    @Override
    public ResponseEntity<UserinfoDTO> getUserInfo(@AuthenticationPrincipal OidcUser oidcUser) {
        if (oidcUser != null) {
            return ResponseEntity.ok(userService.getUserInfo(oidcUser));
        } else {
            return ResponseEntity.ok(null);
        }
    }
}
