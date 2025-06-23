package src.main.java.org.example.weatherforecastapp.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseDto;
import src.main.java.org.example.weatherforecastapp.dto.WeatherResponseV2;
import src.main.java.org.example.weatherforecastapp.service.WeatherService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WeatherController {


private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
@GetMapping("/hey")
 public WeatherResponseDto getResponse(){
    WeatherResponseDto data = weatherService.getData();

    List<Double> doubles = weatherService.calculateEnergyKWh(data.getDaily().getSunshineDuration());

     data.getDaily().setSunshineDuration(doubles);
    return data;

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
