package org.example.weatherforecastapp.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.weatherforecastapp.dto.DailyResponse;
import org.example.weatherforecastapp.dto.WeeklyResponse;
import org.example.weatherforecastapp.service.DailyWeatherService;
import org.example.weatherforecastapp.service.WeeklyWeatherService;
import org.example.weatherforecastapp.service.impl.DailyWeatherServiceImpl;
import org.example.weatherforecastapp.service.impl.WeeklyWeatherServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/weather")
@Validated
public class WeatherController {


    private final DailyWeatherService dailyWeatherService;
    private final WeeklyWeatherService weeklyWeatherService;

    public WeatherController(DailyWeatherService dailyWeatherService, WeeklyWeatherService weeklyWeatherService) {
        this.dailyWeatherService = dailyWeatherService;
        this.weeklyWeatherService = weeklyWeatherService;
    }


    @GetMapping("/daily")
    public DailyResponse getDailyResponse(@RequestParam @Min(-90) @Max(90) double latitude,
                                          @RequestParam @Min(-180) @Max(180) double longitude) {


        return dailyWeatherService.getDailyWeatherForecast(latitude, longitude);
    }


    @GetMapping("/weekly")
    public WeeklyResponse getWeeklyResponse(@RequestParam @Min(-90) @Max(90) double latitude,
                                            @RequestParam @Min(-180) @Max(180) double longitude) {


        return weeklyWeatherService.getWeeklyWeatherForecast(latitude, longitude);

    }

}
