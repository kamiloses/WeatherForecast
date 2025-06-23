package src.main.java.org.example.weatherforecastapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import src.main.java.org.example.weatherforecastapp.dto.FinalResponseWeather;
import src.main.java.org.example.weatherforecastapp.dto.firstRequest.WeatherResponseDto;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseV2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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


    public FinalResponseWeather getDataV2() {

        WeatherResponseV2 weatherResponse = restClient.get()
                .uri("/forecast?latitude=52.52&longitude=13.41&daily=temperature_2m_max,temperature_2m_min,weather_code&hourly=pressure_msl,sunshine_duration")
                .retrieve().body(WeatherResponseV2.class);

        FinalResponseWeather finalResponseWeather = new FinalResponseWeather();


        finalResponseWeather.setAveragePressure(computeAveragePressure(weatherResponse));
        finalResponseWeather.setAverageWeekDuration(getWeeklyAverageSunshineHour(weatherResponse));
        finalResponseWeather.setMinTemperature(getMinTemperaturePerWeek(weatherResponse));
        finalResponseWeather.setMaxTemperature(getMaxTemperaturePerWeek(weatherResponse));


        finalResponseWeather.setWeekSumarization(summarizeWeekByRain(weatherResponse.getDaily().getWeatherCode()));


        return finalResponseWeather;
    }

    static int computeAveragePressure(WeatherResponseV2 weatherResponse) {
        double sum = weatherResponse.getHourly().getPressure().stream().mapToDouble(Double::doubleValue).sum();
        int size = weatherResponse.getHourly().getPressure().size();
        double averagePressure = sum / size;

        return (int) averagePressure;
    }


    //todo drugie pole to jest w drugim requescie . upewnij sie że jest dobrze
    static double getWeeklyAverageSunshineHour(WeatherResponseV2 weatherResponse) {
        List<Double> durationsInSeconds = weatherResponse.getHourly().getSunshineDuration();
        double sumSeconds = durationsInSeconds.stream().mapToDouble(Double::doubleValue).sum();


        double totalHours = sumSeconds / 3600.0;
        double averageHoursPerDay = totalHours / 7.0;

        return Math.round(averageHoursPerDay * 10) / 10.0;
    }


    static int getMinTemperaturePerWeek(WeatherResponseV2 weatherResponseV2) {
        List<Double> temperatureMin = weatherResponseV2.getDaily().getTemperatureMin();
        double minTemp = Collections.min(temperatureMin);
        return (int) Math.round(minTemp);
    }


    static int getMaxTemperaturePerWeek(WeatherResponseV2 weatherResponseV2) {
        List<Double> temperatureMax = weatherResponseV2.getDaily().getTemperatureMax();
        double maxTemp = Collections.max(temperatureMax);
        return (int) Math.round(maxTemp);


    }

    static String summarizeWeekByRain(List<Integer> weatherCodes) {
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





