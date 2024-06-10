package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import be.faros.sandwichbar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    @Override
    public ResponseEntity<UserinfoDTO> getUserInfo(@AuthenticationPrincipal OidcUser oidcUser) {
        if (oidcUser != null) {
            return ResponseEntity.ok(userService.getUserInfo(oidcUser));
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/account")
    @Override
    public void updateUserAccount(@AuthenticationPrincipal OidcUser oidcUser, @RequestBody UpdateUserRequest updateUserRequest) {
        if (oidcUser != null) {
            userService.updateUser(oidcUser, updateUserRequest);
        }
    }

}
