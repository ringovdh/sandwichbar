package be.faros.sandwichbar.sandwich_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class SandwichApiConfig {

    @Value("${sandwich.api.url}")
    String sandwichAPiURL;

    @Bean
    public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {

        var oauthFilter = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                authorizedClientManager);

        oauthFilter.setDefaultClientRegistrationId("sandwich_api");

        return WebClient.builder()
                .baseUrl(sandwichAPiURL)
                .filter(oauthFilter)
                .filter(logRequest())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("Request: " + clientRequest.method() + "__" + clientRequest.url());
            return Mono.just(clientRequest);
        });
    }
}
