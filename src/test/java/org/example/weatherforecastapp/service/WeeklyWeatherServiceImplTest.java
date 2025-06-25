package org.example.weatherforecastapp.service;

import org.example.weatherforecastapp.dto.WeeklyWeatherApiResponseDto;
import org.example.weatherforecastapp.service.impl.WeeklyWeatherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeeklyWeatherServiceImplTest {


    @MockitoBean
    WeeklyWeatherServiceImpl weatherService;


    @Test
    public void should_test_fetchWeeklyWeatherData(){
        WeeklyWeatherApiResponseDto mockDto = new WeeklyWeatherApiResponseDto();
        when(weeklyWeatherService.fetchWeeklyWeatherData(anyDouble(), anyDouble())).thenReturn(mockDto);

        Assertions.assertEquals(null,weeklyWeatherApiResponseDto);

    }


}