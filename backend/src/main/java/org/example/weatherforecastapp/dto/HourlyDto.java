package src.main.java.org.example.weatherforecastapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@Getter
public class HourlyDto {//zmienic nazwe na request v2

    @JsonProperty("time")
    private List<Date> time;

    @JsonProperty("pressure_msl")
    private List<Double> pressure; //todo zmienić nazwe

//    @JsonProperty("sunshine_duration")
//    private List<Double> sunshineDuration;




    //todo 2 pola jewszcze z 2 requesta
}
