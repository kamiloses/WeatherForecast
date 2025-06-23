package src.main.java.org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@Getter
public class HourlyDto {

    @JsonProperty("time")
    private List<Date> time;

    @JsonProperty("pressure_msl")
    private List<Double> pressure;



    @JsonProperty("sunshine_duration")
    private List<Double> sunshineDuration;




}
