package src.main.java.org.example.weatherforecastapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import src.main.java.org.example.weatherforecastapp.dto.WeeklyResponse;
import src.main.java.org.example.weatherforecastapp.dto.WeeklyWeatherApiResponseDto;
import src.main.java.org.example.weatherforecastapp.exception.RestClientFetchingException;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
//Second Request
public class WeeklyWeatherService {

    private final RestClient restClient;

    public WeeklyWeatherService(RestClient restClient) {
        this.restClient = restClient;
    }

    public WeeklyResponse getWeeklyWeatherForecast() {

        WeeklyWeatherApiResponseDto weeklyWeatherResponse = restClient.get()
                .uri("/forecast?latitude=52.52&longitude=13.41&daily=temperature_2m_max,temperature_2m_min,weather_code&hourly=pressure_msl,sunshine_duration")
                .retrieve().body(WeeklyWeatherApiResponseDto.class);
        if (weeklyWeatherResponse == null) {
            throw new RestClientFetchingException("There was some problem with fetching data from the external api");
        }

        return new WeeklyResponse(
                computeAveragePressure(weeklyWeatherResponse),
                getWeeklyAverageSunshineHour(weeklyWeatherResponse),
                getMinTemperaturePerWeek(weeklyWeatherResponse),
                getMaxTemperaturePerWeek(weeklyWeatherResponse),
                summarizeWeekByRain(weeklyWeatherResponse.getDaily().getWeatherCode())
        );
    }

    private static int computeAveragePressure(WeeklyWeatherApiResponseDto weatherResponse) {
        double sum = weatherResponse.getHourly().getPressure().stream().mapToDouble(Double::doubleValue).sum();
        int size = weatherResponse.getHourly().getPressure().size();
        double averagePressure = sum / size;

        return (int) Math.round(averagePressure);
    }


    //TODO UPEWNIJ SIĘ CZY DZIAŁA POPRAWNIE
    private static double getWeeklyAverageSunshineHour(WeeklyWeatherApiResponseDto weatherResponse) {
        List<Double> durationsInSeconds = weatherResponse.getHourly().getSunshineDuration();
        double sumSeconds = durationsInSeconds.stream().mapToDouble(Double::doubleValue).sum();


        double totalHours = sumSeconds / 3600.0;
        double averageHoursPerDay = totalHours / 7.0;

        return Math.round(averageHoursPerDay * 10) / 10.0;
    }


    private static int getMinTemperaturePerWeek(WeeklyWeatherApiResponseDto weeklyWeatherApiResponseDto) {
        List<Double> temperatureMin = weeklyWeatherApiResponseDto.getDaily().getTemperatureMin();
        double minTemp = Collections.min(temperatureMin);
        return (int) Math.round(minTemp);
    }


    private static int getMaxTemperaturePerWeek(WeeklyWeatherApiResponseDto weeklyWeatherApiResponseDto) {
        List<Double> temperatureMax = weeklyWeatherApiResponseDto.getDaily().getTemperatureMax();
        double maxTemp = Collections.max(temperatureMax);
        return (int) Math.round(maxTemp);


    }

    private static String summarizeWeekByRain(List<Integer> weatherCodes) {
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
