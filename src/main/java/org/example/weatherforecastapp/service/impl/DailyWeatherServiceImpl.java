package org.example.weatherforecastapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecastapp.dto.DailyResponse;
import org.example.weatherforecastapp.dto.DailyWeatherApiResponseDto;
import org.example.weatherforecastapp.exception.RestClientFetchingException;
import org.example.weatherforecastapp.service.DailyWeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
//First Request
public class DailyWeatherServiceImpl implements DailyWeatherService {

    private final RestClient restClient;

    public DailyWeatherServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public DailyWeatherApiResponseDto fetchDailyWeatherData(double latitude, double longitude) {

        DailyWeatherApiResponseDto dailyWeatherResponse = restClient.get()
                .uri(String.format(Locale.US,
                        "/forecast?latitude=%f&longitude=%f&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration",
                        latitude, longitude))
                .retrieve().body(DailyWeatherApiResponseDto.class);

        if (dailyWeatherResponse == null) {
            throw new RestClientFetchingException("There was some problem with fetching data from the external api");
        }
        log.info("Successfully fetched weather data");


        return dailyWeatherResponse;
    }

    @Override
    public DailyResponse getDailyWeatherForecast(double latitude, double longitude) {
        DailyWeatherApiResponseDto apiResponse = fetchDailyWeatherData(latitude, longitude);
        return mapToDailyResponse(apiResponse);
    }







    public static List<Double> calculateEnergyKWh(List<Double> sunshineDurations) {
        double powerKW = 2.5;
        double efficiency = 0.2;

        return sunshineDurations.stream()
                .map(seconds -> {
                    double energy = (seconds / 3600.0) * powerKW * efficiency;
                    return new BigDecimal(energy).setScale(1, RoundingMode.HALF_UP).doubleValue();
                }).toList();
    }


    public static DailyResponse mapToDailyResponse(DailyWeatherApiResponseDto dailyWeatherResponse) {
        return new DailyResponse(
                dailyWeatherResponse.getDaily().getTime(),
                dailyWeatherResponse.getDaily().getWeatherCode(),
                dailyWeatherResponse.getDaily().getTemperatureMax(),
                dailyWeatherResponse.getDaily().getTemperatureMin(),
                calculateEnergyKWh(dailyWeatherResponse.getDaily().getSunshineDuration())
        );
    }

}
