package src.main.java.org.example.weatherforecastapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {

        return RestClient.builder()
                .baseUrl("https://api.open-meteo.com/v1")
                .build();
    }


}
