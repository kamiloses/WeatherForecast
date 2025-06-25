package org.example.weatherforecastapp.service;

import org.example.weatherforecastapp.dto.WeeklyResponse;
import org.example.weatherforecastapp.dto.WeeklyWeatherApiResponseDto;

public interface WeeklyWeatherService {


    WeeklyWeatherApiResponseDto fetchWeeklyWeatherData(double latitude, double longitude);
    WeeklyResponse mapToWeeklyResponse(WeeklyWeatherApiResponseDto weeklyWeatherResponse);

}
