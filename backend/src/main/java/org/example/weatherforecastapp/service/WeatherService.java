package src.main.java.org.example.weatherforecastapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseDto;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseV2;

@Service
public class WeatherService {

    private final RestClient restClient;

    public WeatherService(RestClient restClient) {
        this.restClient = restClient;
    }

    public WeatherResponseDto getData() {

        return restClient.get()
                .uri("/forecast?latitude=52.52&longitude=13.41&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration")
                .retrieve().body(WeatherResponseDto.class);
    }


    public WeatherResponseV2 getDataV2() {

        return restClient.get()
                .uri("/forecast?latitude=52.52&longitude=13.41&daily=sunshine_duration&hourly=pressure_msl")
                .retrieve().body(WeatherResponseV2.class);
    }


}





