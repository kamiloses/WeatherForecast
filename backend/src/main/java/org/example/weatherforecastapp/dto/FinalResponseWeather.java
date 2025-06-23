package src.main.java.org.example.weatherforecastapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FinalResponseWeather {

    private int averagePressure;

    private double averageWeekDuration;//todo popraw nazwe

    private int minTemperature;

    private int maxTemperature;

    private String weekSumarization;//todo zmien

}
