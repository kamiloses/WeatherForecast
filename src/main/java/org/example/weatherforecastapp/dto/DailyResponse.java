package org.example.weatherforecastapp.dto;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyResponse {


    private List<String> time;

    private List<Integer> weatherCode;

    private List<Double> temperatureMax;

    private List<Double> temperatureMin;

    private List<Double> estimatedEnergyKWh;

}
