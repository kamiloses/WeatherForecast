package org.example.weatherforecastapp.externalApi;

import org.example.weatherforecastapp.dto.DailyWeatherApiResponseDto;
import org.example.weatherforecastapp.dto.WeeklyWeatherApiResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ExternalApiConnectionTest {
    @Autowired
    RestClient restClient;


    double latitude = 30;
    double longitude = 30;

    @Test
    public void testFirstExternalEndpoint() {


        var response = restClient.get()
                .uri(String.format(Locale.US,
                        "/forecast?latitude=%f&longitude=%f&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration",
                        latitude, longitude))
                .retrieve().body(DailyWeatherApiResponseDto.class);



        assertNotNull(response.getDaily().getTime());
        assertNotNull(response.getDaily().getWeatherCode());
        assertNotNull(response.getDaily().getTemperatureMax());
        assertNotNull(response.getDaily().getTemperatureMin());
        assertNotNull(response.getDaily().getSunshineDuration());

    }

    @Test
    public void testSecondExternalEndpoint() {
       var response=restClient.get()
                .uri(String.format(Locale.US,
                        "/forecast?latitude=%.6f&longitude=%.6f&daily=temperature_2m_max,temperature_2m_min,weather_code&hourly=pressure_msl,sunshine_duration",
                        latitude, longitude))
                .retrieve().body(WeeklyWeatherApiResponseDto.class);


        assertNotNull(response.getDaily().getTime());
        assertNotNull(response.getDaily().getWeatherCode());
        assertNotNull(response.getDaily().getTemperatureMax());
        assertNotNull(response.getDaily().getTemperatureMin());
        assertNotNull(response.getHourly().getPressure());
        assertNotNull(response.getHourly().getSunshineDuration());

    }


}
