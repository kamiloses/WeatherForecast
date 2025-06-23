package src.main.java.org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Getter
public class WeatherResponseV2 {


    @JsonProperty("hourly")
    private HourlyDto hourly;


}
