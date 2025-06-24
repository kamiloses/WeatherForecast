package org.example.weatherforecastapp.controller;

import org.example.weatherforecastapp.dto.DailyResponse;
import org.example.weatherforecastapp.dto.WeeklyResponse;
import org.example.weatherforecastapp.service.DailyWeatherService;
import org.example.weatherforecastapp.service.WeeklyWeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/weather")
public class WeatherController {


    private final DailyWeatherService dailyWeatherService;
    private final WeeklyWeatherService weeklyWeatherService;

    public WeatherController(DailyWeatherService dailyWeatherService, WeeklyWeatherService weeklyWeatherService) {
        this.dailyWeatherService = dailyWeatherService;
        this.weeklyWeatherService = weeklyWeatherService;
    }


    @GetMapping("/daily")
    public DailyResponse getDailyResponse(@RequestParam double latitude, @RequestParam double longitude) {



        return dailyWeatherService.getDailyWeatherForecast(latitude, longitude);
    }


    @GetMapping("/weekly")
    public WeeklyResponse getWeeklyResponse(@RequestParam double latitude, @RequestParam double longitude) {



        return weeklyWeatherService.getWeeklyWeatherForecast(latitude, longitude);

    }

}
