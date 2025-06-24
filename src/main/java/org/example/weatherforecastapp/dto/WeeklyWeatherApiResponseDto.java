package org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WeeklyWeatherApiResponseDto {


    @JsonProperty("hourly")
    private HourlyDto hourly;

    @JsonProperty("daily")
    private DailyDto daily;



}
