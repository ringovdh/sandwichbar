package be.faros.sandwichbar.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth -> oauth.successHandler(oAuth2loginSuccessHandler))
                .logout(l -> l.addLogoutHandler(logoutHandler()))
                .build();
    }

    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try {
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + frontendURL);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
