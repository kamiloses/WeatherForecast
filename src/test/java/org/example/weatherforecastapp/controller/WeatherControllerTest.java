package org.example.weatherforecastapp.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetDailyResponse() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/weather/daily")
                        .queryParam("longitude", "25.4")
                        .queryParam("latitude", "36")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.time").isArray())
                .andExpect(jsonPath("$.temperatureMax").isArray())
                .andExpect(jsonPath("$.temperatureMin").isArray())
                .andExpect(jsonPath("$.weatherCode").isArray()).andReturn();

        System.err.println(result.getResponse().getContentAsString());

    }


    @Test
    void testInvalidGetDailyResponse() throws Exception {
        mockMvc.perform(get("/api/v1/weather/daily")
                        .param("longitude", "100")
                        .param("latitude", "100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Latitude must be between -90 and 90, and longitude between -180 and 180"));
    }


    @Test
    void testGetWeeklyResponse() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/weather/weekly")
                        .queryParam("longitude", "25.4")
                        .queryParam("latitude", "36")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.averagePressure").isNumber())
                .andExpect(jsonPath("$.averageSunshineDuration").isNumber())
                .andExpect(jsonPath("$.minTemperature").isNumber())
                .andExpect(jsonPath("$.maxTemperature").isNumber())
                .andExpect(jsonPath("$.rainSummary").isString()).andReturn();

        System.err.println(result.getResponse().getContentAsString());


    }

    @Test
    void testInvalidGetWeeklyResponse() throws Exception {
        mockMvc.perform(get("/api/v1/weather/weekly")
                        .param("longitude", "100")
                        .param("latitude", "100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Latitude must be between -90 and 90, and longitude between -180 and 180"));

    }


}