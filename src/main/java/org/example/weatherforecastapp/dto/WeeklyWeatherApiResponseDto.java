package org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WeeklyWeatherApiResponseDto {


    @JsonProperty("hourly")
    private HourlyDto hourly;

    @JsonProperty("daily")
    private DailyDto daily;



}
