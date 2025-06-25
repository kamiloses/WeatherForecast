package org.example.weatherforecastapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecastapp.dto.WeeklyResponse;
import org.example.weatherforecastapp.dto.WeeklyWeatherApiResponseDto;
import org.example.weatherforecastapp.exception.RestClientFetchingException;
import org.example.weatherforecastapp.service.WeeklyWeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@Slf4j
public class WeeklyWeatherServiceImpl implements WeeklyWeatherService {

    private final RestClient restClient;

    public WeeklyWeatherServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public WeeklyWeatherApiResponseDto fetchWeeklyWeatherData(double latitude, double longitude) {
        WeeklyWeatherApiResponseDto weeklyWeatherResponse = restClient.get()
                .uri(String.format(Locale.US,
                        "/forecast?latitude=%.6f&longitude=%.6f&daily=temperature_2m_max,temperature_2m_min,weather_code&hourly=pressure_msl,sunshine_duration",
                        latitude, longitude))
                .retrieve().body(WeeklyWeatherApiResponseDto.class);

        if (weeklyWeatherResponse == null) {
            throw new RestClientFetchingException("There was some problem with fetching data from the external api");
        }
        log.info("Successfully fetched weekly weather data");
        return weeklyWeatherResponse;
    }

    @Override
    public WeeklyResponse getWeeklyWeatherForecast(double latitude, double longitude) {
        WeeklyWeatherApiResponseDto apiResponse = fetchWeeklyWeatherData(latitude, longitude);
        return mapToWeeklyResponse(apiResponse);
    }



    public WeeklyResponse mapToWeeklyResponse(WeeklyWeatherApiResponseDto weeklyWeatherResponse) {
        return new WeeklyResponse(
                computeAveragePressure(weeklyWeatherResponse),
                getWeeklyAverageSunshineHour(weeklyWeatherResponse),
                getMinTemperaturePerWeek(weeklyWeatherResponse),
                getMaxTemperaturePerWeek(weeklyWeatherResponse),
                summarizeWeekByRain(weeklyWeatherResponse.getDaily().getWeatherCode())
        );
    }

    public static int computeAveragePressure(WeeklyWeatherApiResponseDto weatherResponse) {
        double sum = weatherResponse.getHourly().getPressure().stream().mapToDouble(Double::doubleValue).sum();
        int size = weatherResponse.getHourly().getPressure().size();
        double averagePressure = sum / size;
        return (int) Math.round(averagePressure);
    }

    public static double getWeeklyAverageSunshineHour(WeeklyWeatherApiResponseDto weatherResponse) {
        List<Double> durationsInSeconds = weatherResponse.getHourly().getSunshineDuration();
        double sumSeconds = durationsInSeconds.stream().mapToDouble(Double::doubleValue).sum();
        double totalHours = sumSeconds / 3600.0;
        double averageHoursPerDay = totalHours / 7.0;
        return Math.round(averageHoursPerDay * 10) / 10.0;
    }

    public static double getMinTemperaturePerWeek(WeeklyWeatherApiResponseDto weeklyWeatherApiResponseDto) {
        List<Double> temperatureMin = weeklyWeatherApiResponseDto.getDaily().getTemperatureMin();
        return Collections.min(temperatureMin);
    }

    public static double getMaxTemperaturePerWeek(WeeklyWeatherApiResponseDto weeklyWeatherApiResponseDto) {
        List<Double> temperatureMax = weeklyWeatherApiResponseDto.getDaily().getTemperatureMax();
        return Collections.max(temperatureMax);
    }

    public static String summarizeWeekByRain(List<Integer> weatherCodes) {
        int rainDays = 0;
        Set<Integer> rainCodes = Set.of(51, 53, 55, 61, 63, 65, 80, 81, 82);
        for (Integer code : weatherCodes) {
            if (rainCodes.contains(code)) {
                rainDays++;
            }
        }
        if (rainDays >= 4) {
            return "Rainy week";
        } else if (rainDays >= 2) {
            return "Light rain days";
        } else {
            return "Week without rain";
        }
    }
}
