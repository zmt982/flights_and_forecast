package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Forecast {
    private String city;
    private List<WeatherForecast> weatherForecast;
}