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

@Configuration
@EnableWebSecurity
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth -> oauth.successHandler(oAuth2loginSuccessHandler))
                .logout(logout -> logout
                        .logoutSuccessUrl(frontendURL)
                )
                .build();
    }

}
