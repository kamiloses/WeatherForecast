package src.main.java.org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WeatherResponseDto {

    @JsonProperty("daily")
    private DailyDto daily;}

