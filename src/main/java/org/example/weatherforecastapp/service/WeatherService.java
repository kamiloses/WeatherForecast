package org.example.weatherforecastapp.service;

import java.net.http.HttpClient;
import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
public class WeatherService {

    private final RestClient restClient;

    public WeatherService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getData(){

        String response = restClient.get()
                .uri("/forecast?latitude=52.52&longitude=13.41&daily=weather_code,temperature_2m_max,temperature_2m_min")
                .retrieve().body(String.class);



    return response;}

}





