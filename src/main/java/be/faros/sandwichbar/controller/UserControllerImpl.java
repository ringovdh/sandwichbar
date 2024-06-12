package be.faros.sandwichbar.controller;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import be.faros.sandwichbar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping()
    @Override
    public ResponseEntity<UserinfoDTO> getUserInfo(@AuthenticationPrincipal OidcUser oidcUser) {
        return ResponseEntity.ok(userService.getUserInfo(oidcUser));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("")
    @Override
    public void updateUserInfo(@AuthenticationPrincipal OidcUser oidcUser, @RequestBody UpdateUserRequest updateUserRequest) {
            userService.updateUserInfo(oidcUser, updateUserRequest);
    }

}
