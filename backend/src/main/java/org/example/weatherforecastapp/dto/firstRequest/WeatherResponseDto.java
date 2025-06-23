package src.main.java.org.example.weatherforecastapp.dto.firstRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WeatherResponseDto {

    @JsonProperty("daily")
    private DailyDto daily;}

