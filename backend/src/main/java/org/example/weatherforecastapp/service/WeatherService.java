package src.main.java.org.example.weatherforecastapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseDto;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseV2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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

        //i to jest otatnie pole
    public List<Double> calculateEnergyKWh(List<Double> sunshineDurations) {
        double powerKW = 2.5;
        double efficiency = 0.2;

       return sunshineDurations.stream()
                .map(seconds -> {
                    double energy = (seconds / 3600.0) * powerKW * efficiency;
                    return new BigDecimal(energy).setScale(1, RoundingMode.HALF_UP).doubleValue();
                }).toList();
    }




    public WeatherResponseV2 getDataV2() {

        return restClient.get()
                .uri("/forecast?latitude=52.52&longitude=13.41&daily=sunshine_duration&hourly=pressure_msl")
                .retrieve().body(WeatherResponseV2.class);
    }


}





