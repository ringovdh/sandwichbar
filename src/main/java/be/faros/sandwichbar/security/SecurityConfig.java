package be.faros.sandwichbar.security;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${frontend.url}")
    private String frontendURL;

    private final OAuth2loginSuccessHandler oAuth2loginSuccessHandler;

    public SecurityConfig(OAuth2loginSuccessHandler oAuth2loginSuccessHandler) {
        this.oAuth2loginSuccessHandler = oAuth2loginSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository) {
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri(frontendURL);
        return successHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/home").permitAll()
                        .requestMatchers("/WEB-INF/**", "/resources/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth -> {
                    oauth.successHandler(oAuth2loginSuccessHandler);
                    oauth.userInfoEndpoint(userInfo -> userInfo
                            .oidcUserService(this.oidcUserService()));
                })
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler)
                )
                .build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService userService = new OidcUserService();

        return userRequest -> {
            OidcUser oidcUser = userService.loadUser(userRequest);
            OAuth2AccessToken accessToken = userRequest.getAccessToken();
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            try {
                JWT jwt = JWTParser.parse(accessToken.getTokenValue());
                JWTClaimsSet claimSet = jwt.getJWTClaimsSet();
                Collection<String> userAuthorities = claimSet.getStringListClaim("authorities");
                mappedAuthorities.addAll(userAuthorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList());
            } catch (ParseException e) {
                System.err.println("Error OAuth2UserService: " + e.getMessage());
            }
            oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
            return oidcUser;
        };
    }

}
