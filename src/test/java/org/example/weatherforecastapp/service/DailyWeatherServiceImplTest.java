package org.example.weatherforecastapp.service;

import org.example.weatherforecastapp.service.impl.DailyWeatherServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DailyWeatherServiceImplTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/energy_calculation_cases.csv", numLinesToSkip = 1, delimiter = '\t')
    public void should_calculate_energyKWh_from_csv(String sunshineDurationsCsv, String expectedEnergyCsv) {
        List<Double> sunshineDurations = parseCsvToDoubleList(sunshineDurationsCsv);
        List<Double> expectedEnergy = parseCsvToDoubleList(expectedEnergyCsv);

        List<Double> result = DailyWeatherServiceImpl.calculateEnergyKWh(sunshineDurations);

        assertEquals(expectedEnergy, result);
    }


    private List<Double> parseCsvToDoubleList(String csv) {
        return Arrays.stream(csv.replace("\"", "").split(";"))
                .map(String::trim)
                .map(Double::parseDouble)
                .collect(Collectors.toList());


    }

}


