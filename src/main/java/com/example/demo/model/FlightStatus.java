package com.example.demo.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FlightStatus {
    private List<Flight> flights;
    private Map<String, List<WeatherForecast>> forecast;
}