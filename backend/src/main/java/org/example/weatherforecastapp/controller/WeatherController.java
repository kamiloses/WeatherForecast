package src.main.java.org.example.weatherforecastapp.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseDto;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseV2;
import src.main.java.org.example.weatherforecastapp.service.WeatherService;

@RestController
public class WeatherController {


private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
@GetMapping("/hey")
 public WeatherResponseDto getResponse(){

       return weatherService.getData();

 }
@GetMapping
String test(){
        return "HEJ";
}

 @GetMapping("/hey2")
    public WeatherResponseV2 getSecondResponse(){
     return weatherService.getDataV2();

 }

}
