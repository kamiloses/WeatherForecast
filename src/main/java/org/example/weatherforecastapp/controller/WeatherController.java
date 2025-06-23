package org.example.weatherforecastapp.controller;

import org.example.weatherforecastapp.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {


private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
@GetMapping("/hey")
 public String getResponse(){

       return weatherService.getData();

 }

}
