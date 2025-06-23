package src.main.java.org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import src.main.java.org.example.weatherforecastapp.dto.firstRequest.DailyDto;

@NoArgsConstructor
@Getter
public class WeatherResponseV2 {


    @JsonProperty("hourly")
    private HourlyDto hourly;

    @JsonProperty("daily")
    private DailyDto daily;



}
