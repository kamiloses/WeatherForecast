package src.main.java.org.example.weatherforecastapp.controller;

import org.springframework.web.bind.annotation.*;
import src.main.java.org.example.weatherforecastapp.dto.DailyResponse;
import src.main.java.org.example.weatherforecastapp.dto.WeeklyResponse;
import src.main.java.org.example.weatherforecastapp.service.DailyWeatherService;
import src.main.java.org.example.weatherforecastapp.service.WeeklyWeatherService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/weatherApi")
public class WeatherController {

    //TODO WALIDACJA TESTY DYNAMICZNE ENDPOINTY
    private final DailyWeatherService dailyWeatherService;
    private final WeeklyWeatherService weeklyWeatherService;

    public WeatherController(DailyWeatherService dailyWeatherService, WeeklyWeatherService weeklyWeatherService) {
        this.dailyWeatherService = dailyWeatherService;
        this.weeklyWeatherService = weeklyWeatherService;
    }


    @GetMapping("/hey")
    public DailyResponse getResponse(@RequestParam double latitude, @RequestParam double longitude) {
        latitude=52.52;
        longitude=13.41;

        return dailyWeatherService.getDailyWeatherForecast(latitude,longitude);
    }


    @GetMapping("/hey2")
    public WeeklyResponse getSecondResponse(@RequestParam double latitude, @RequestParam double longitude) {
        latitude=52.52;
        longitude=13.41;

        return weeklyWeatherService.getWeeklyWeatherForecast(latitude,longitude);

    }

}
