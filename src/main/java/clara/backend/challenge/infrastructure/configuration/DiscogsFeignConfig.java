package clara.backend.challenge.infrastructure.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscogsFeignConfig {

    private final String token;

    public DiscogsFeignConfig(@Value("${api.external.discogs.access-token}") String token) {
        this.token = token;
    }

    @Bean
    public RequestInterceptor tokenRequestInterceptor() {
        return requestTemplate -> requestTemplate.query("token", token);
    }

}
