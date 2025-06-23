package org.example.weatherforecastapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DailyWeatherDTO {

    private Date date;
    private Integer weatherCode;
    private Float minTemperature;
    private Float maxTemperature;

}
