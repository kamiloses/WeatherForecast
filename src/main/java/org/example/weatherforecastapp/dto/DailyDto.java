package org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyDto {
    @JsonProperty("time")
    private List<String> time;

    @JsonProperty("weather_code")
    private List<Integer> weatherCode;

    @JsonProperty("temperature_2m_max")
    private List<Double> temperatureMax;

    @JsonProperty("temperature_2m_min")
    private List<Double> temperatureMin;

    @JsonProperty("sunshine_duration")
    private List<Double> sunshineDuration;
}
