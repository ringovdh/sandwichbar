package be.faros.sandwichbar.service;

import be.faros.sandwichbar.dto.UserinfoDTO;
import be.faros.sandwichbar.dto.request.UpdateUserRequest;
import be.faros.sandwichbar.entity.User;
import be.faros.sandwichbar.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserinfoDTO getUserInfo(OidcUser oidcUser) {
        Optional<User> user = userRepository.findByUserRef(oidcUser.getName());
        if (user.isPresent()) {
            return createLoginResponse(user.get(), oidcUser.getAuthorities());
        } else {
            User newUser = createUser(oidcUser);
            return createLoginResponse(newUser, oidcUser.getAuthorities());
        }
    }

    @Override
    public void updateUserInfo(OidcUser oidcUser, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findByUserRef(oidcUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        user.setUsername(updateUserRequest.username());
        userRepository.save(user);
    }

    private User createUser(OidcUser oidcUser) {
        User user = new User();
        user.setName(oidcUser.getFullName());
        user.setEmail(oidcUser.getName());
        user.setUserRef(oidcUser.getName());
        userRepository.save(user);
        return user;
    }

    private UserinfoDTO createLoginResponse(User user, Collection<? extends GrantedAuthority> authorities) {
        return new UserinfoDTO(
                user.getUserRef(),
                user.getUsername(),
                user.getEmail(),
                authorities.stream().map(GrantedAuthority::getAuthority).toList()
        );
    }
}
