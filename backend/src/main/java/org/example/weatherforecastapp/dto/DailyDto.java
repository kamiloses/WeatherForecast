package src.main.java.org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class DailyDto {
    @JsonProperty("time")
    private List<String> time;

    @JsonProperty("weather_code")
    private List<Integer> weatherCode;

    @JsonProperty("temperature_2m_max")
    private List<Integer> temperatureMax;

    @JsonProperty("temperature_2m_min")
    private List<Integer> temperatureMin;

    @JsonProperty("sunshine_duration")
    private List<Double> sunshineDuration;

}
