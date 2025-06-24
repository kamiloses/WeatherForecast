package org.example.weatherforecastapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyResponse {


    private int averagePressure;

    private double averageSunshineDuration;

    private double minTemperature;

    private double maxTemperature;

    private String rainSummary;

}
