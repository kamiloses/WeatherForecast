package org.example.weatherforecastapp.service;

import org.example.weatherforecastapp.dto.DailyDto;
import org.example.weatherforecastapp.dto.HourlyDto;
import org.example.weatherforecastapp.dto.WeeklyWeatherApiResponseDto;
import org.example.weatherforecastapp.service.impl.WeeklyWeatherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WeeklyWeatherServiceImplTest {


   // @MockitoBean
    WeeklyWeatherService weeklyWeatherService;

//    @BeforeAll
//    void setUp(){
////        when(weeklyWeatherService.fetchWeeklyWeatherData(anyDouble(), anyDouble()))
////                .thenReturn(createWeeklyApiResponse());
//    }




    @Test
    public void summarizeWeekByRainTest(){
        WeeklyWeatherApiResponseDto weeklyApiResponse = new WeeklyWeatherApiResponseDto();
        weeklyApiResponse.setDaily(new DailyDto());

        // clear, rain, rain, clear, rain, clear, rain
        List<Integer> weatherCodes1 = List.of(0, 61, 63, 0, 55, 0, 80);
        weeklyApiResponse.getDaily().setWeatherCode(weatherCodes1);

        String rainyWeek = WeeklyWeatherServiceImpl.summarizeWeekByRain(weeklyApiResponse.getDaily().getWeatherCode());
        Assertions.assertEquals("Rainy week",rainyWeek);

       // clear, rain, clear, clear, clear, clear, rain
        List<Integer> weatherCodes2 = List.of(0, 61, 0, 0, 0, 0, 63);
        weeklyApiResponse.getDaily().setWeatherCode(weatherCodes2);
        String lightRainDays = WeeklyWeatherServiceImpl.summarizeWeekByRain(weeklyApiResponse.getDaily().getWeatherCode());
        Assertions.assertEquals("Light rain days",lightRainDays);

         //clear, clear, clear. clear. clear. clear. clear
        List<Integer> weatherCodes3 = List.of(0, 0, 0, 0, 0, 0, 0);
        weeklyApiResponse.getDaily().setWeatherCode(weatherCodes3);
        String weekWithoutRain = WeeklyWeatherServiceImpl.summarizeWeekByRain(weeklyApiResponse.getDaily().getWeatherCode());
         Assertions.assertEquals("Week without rain",weekWithoutRain);


    }
    @Test
    public void testMaxTemperature(){
        DailyDto daily = new DailyDto();
        daily.setTemperatureMax(List.of(24.0, 25.5, 26.0, 27.5, 23.0, 22.5, 24.5));
        WeeklyWeatherApiResponseDto response = new WeeklyWeatherApiResponseDto();
        response.setDaily(daily);
      Assertions.assertEquals(27.5,WeeklyWeatherServiceImpl.getMaxTemperaturePerWeek(response));
    }

    @Test
    public void testMinTemperature(){
        DailyDto daily = new DailyDto();
        daily.setTemperatureMin(List.of(24.0, 25.5, 26.0, 27.5, 23.0, 22.5, 24.5));
        WeeklyWeatherApiResponseDto response = new WeeklyWeatherApiResponseDto();
        response.setDaily(daily);
        Assertions.assertEquals(22.5,WeeklyWeatherServiceImpl.getMinTemperaturePerWeek(response));
    }

    @Test
    public void testComputeAveragePressure() {
        HourlyDto hourly = new HourlyDto();
        hourly.setPressure(List.of(1012.0, 1013.5, 1015.0, 1014.2, 1011.7, 1012.9, 1013.0));

        WeeklyWeatherApiResponseDto response = new WeeklyWeatherApiResponseDto();
        response.setHourly(hourly);

        int averagePressure = WeeklyWeatherServiceImpl.computeAveragePressure(response);

        int expected = 1013;

        Assertions.assertEquals(expected, averagePressure);
    }

    @Test
    public void testGetWeeklyAverageSunshineHour() {
        HourlyDto hourly = new HourlyDto();
        List<Double> sunshineSeconds = List.of(7200.0, 6800.0, 7000.0, 7500.0, 6000.0, 5000.0, 6500.0);
        hourly.setSunshineDuration(sunshineSeconds);

        WeeklyWeatherApiResponseDto response = new WeeklyWeatherApiResponseDto();
        response.setHourly(hourly);

        double result = WeeklyWeatherServiceImpl.getWeeklyAverageSunshineHour(response);


        double expected = 1.8;

        Assertions.assertEquals(expected, result);
    }









//    public  WeeklyWeatherApiResponseDto createWeeklyApiResponse() {
//        HourlyDto hourly = new HourlyDto();
//        hourly.setTime(Arrays.asList(new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date()));
//        hourly.setPressure(Arrays.asList(1012.0, 1013.5, 1015.0, 1014.2, 1011.7, 1012.9, 1013.0));
//        hourly.setSunshineDuration(Arrays.asList(3600.0, 4000.0, 4200.0, 3800.0, 3000.0, 3900.0, 4100.0));
//
//        List<String> days = List.of(
//                "2025-06-25", "2025-06-26", "2025-06-27", "2025-06-28",
//                "2025-06-29", "2025-06-30", "2025-07-01"
//        );
//
//        List<Double> tempMax = List.of(24.0, 25.5, 26.0, 27.5, 23.0, 22.5, 24.5);
//        List<Double> tempMin = List.of(14.0, 15.0, 16.0, 14.5, 13.5, 13.0, 14.0);
//        List<Integer> weatherCodes = List.of(0, 61, 63, 0, 55, 0, 80);
//        List<Double> dailySunshine = List.of(7200.0, 6800.0, 7000.0, 7500.0, 6000.0, 5000.0, 6500.0);
//
//        DailyDto daily = new DailyDto(days, weatherCodes, tempMax, tempMin, dailySunshine);
//
//        WeeklyWeatherApiResponseDto response = new WeeklyWeatherApiResponseDto();
//        response.setHourly(hourly);
//        response.setDaily(daily);
//
//        return response;
//
//
//
//}
}