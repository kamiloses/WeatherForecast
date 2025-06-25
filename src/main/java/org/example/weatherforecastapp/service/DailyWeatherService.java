package org.example.weatherforecastapp.service;

import org.example.weatherforecastapp.dto.DailyResponse;
import org.example.weatherforecastapp.dto.DailyWeatherApiResponseDto;

import java.util.List;

public interface DailyWeatherService {
    DailyWeatherApiResponseDto fetchDailyWeatherData(double latitude, double longitude);
    DailyResponse getDailyWeatherForecast(double latitude, double longitude);
}