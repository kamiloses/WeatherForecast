package org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Data
public class DailyWeatherApiResponseDto {

    @JsonProperty("daily")
    private DailyDto daily;}

