package src.main.java.org.example.weatherforecastapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DailyResponse {


    private List<String> time;

    private List<Integer> weatherCode;

    private List<Double> temperatureMax;

    private List<Double> temperatureMin;

    private List<Double> estimatedEnergyKWh;

}
