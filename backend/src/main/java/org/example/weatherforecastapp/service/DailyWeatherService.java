package src.main.java.org.example.weatherforecastapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import src.main.java.org.example.weatherforecastapp.dto.DailyResponse;
import src.main.java.org.example.weatherforecastapp.dto.DailyWeatherApiResponseDto;
import src.main.java.org.example.weatherforecastapp.exception.RestClientFetchingException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Slf4j
//First Request
public class DailyWeatherService {

    private final RestClient restClient;

    public DailyWeatherService(RestClient restClient) {
        this.restClient = restClient;
    }


    public DailyResponse getDailyWeatherForecast() {

        DailyWeatherApiResponseDto dailyWeatherResponse = restClient.get()
                .uri("/forecast?latitude=52.52&longitude=13.41&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration")
                .retrieve().body(DailyWeatherApiResponseDto.class);

        if (dailyWeatherResponse == null) {
            throw new RestClientFetchingException("There was some problem with fetching data from the external api");
        }
        log.info("Successfully fetched weather data");


        return new DailyResponse(dailyWeatherResponse.getDaily().getTime(),
                dailyWeatherResponse.getDaily().getWeatherCode(),
                dailyWeatherResponse.getDaily().getTemperatureMax(),
                dailyWeatherResponse.getDaily().getTemperatureMin(),
                calculateEnergyKWh(dailyWeatherResponse.getDaily().getSunshineDuration()));

    }


    private static List<Double> calculateEnergyKWh(List<Double> sunshineDurations) {
        double powerKW = 2.5;
        double efficiency = 0.2;

        return sunshineDurations.stream()
                .map(seconds -> {
                    double energy = (seconds / 3600.0) * powerKW * efficiency;
                    return new BigDecimal(energy).setScale(1, RoundingMode.HALF_UP).doubleValue();
                }).toList();
    }


}
