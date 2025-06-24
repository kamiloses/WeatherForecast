package org.example.weatherforecastapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@WireMockTest(httpPort = 8081)
class DailyWeatherServiceTest {




        @Test public void should_calculate_EnergyKWh(){

            List<Double> sunshineDurations = List.of(1.0, 1800.0, 3600.0, 7200.0, 0.0);
            List<Double> expected = List.of(0.0, 0.3, 0.5, 1.0, 0.0);

            List<Double> result = DailyWeatherService.calculateEnergyKWh(sunshineDurations);

            assertEquals(expected, result);
        }


        }