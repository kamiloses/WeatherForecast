package src.main.java.org.example.weatherforecastapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WeeklyResponse {


    private int averagePressure;

    private double averageSunshineDuration;

    private int minTemperature;

    private int maxTemperature;

    private String rainSummary;

}
