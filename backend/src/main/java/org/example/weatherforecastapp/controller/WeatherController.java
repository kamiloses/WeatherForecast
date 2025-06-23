package src.main.java.org.example.weatherforecastapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import src.main.java.org.example.weatherforecastapp.dto.DailyResponse;
import src.main.java.org.example.weatherforecastapp.dto.WeeklyResponse;
import src.main.java.org.example.weatherforecastapp.service.DailyWeatherService;
import src.main.java.org.example.weatherforecastapp.service.WeeklyWeatherService;

@RestController
@CrossOrigin(origins = "*")
public class WeatherController {

//TODO WALIDACJA TESTY DYNAMICZNE ENDPOINTY
private final DailyWeatherService dailyWeatherService;
private final WeeklyWeatherService weeklyWeatherService;

    public WeatherController(DailyWeatherService dailyWeatherService, WeeklyWeatherService weeklyWeatherService) {
        this.dailyWeatherService = dailyWeatherService;
        this.weeklyWeatherService = weeklyWeatherService;
    }


    @GetMapping("/hey")
 public DailyResponse getResponse(){
   return dailyWeatherService.getDailyWeatherForecast();
 }


 @GetMapping("/hey2")
    public WeeklyResponse getSecondResponse(){
     return weeklyWeatherService.getWeeklyWeatherForecast();

 }

}
