package com.example.demo.service;

import com.example.demo.model.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Data
public class FlightStatusServiceImpl implements FlightStatusService {
    private static final Map<String, Integer> UTC_MAP = Map.of("moscow", 0, "novosibirsk", 4, "omsk", 3);

    @Override
    public void getFlightStatus(FlightStatus flightStatus) {
        Map<String, List<WeatherForecast>> forecast = flightStatus.getForecast();
        flightStatus.getFlights().forEach(flight -> {
            if (isFlightWeather(flight, forecast)) {
                System.out.printf("%s | %s -> %s | по расписанию%n", flight.getNo(), flight.getFrom(), flight.getTo());
            } else {
                System.out.printf("%s | %s -> %s | отменен%n", flight.getNo(), flight.getFrom(),
                        flight.getTo());
            }
        });
    }

    private boolean isFlightWeather(Flight flight, Map<String, List<WeatherForecast>> forecast) {
        List<WeatherForecast> weatherListFrom = forecast.get(String.valueOf(flight.getFrom()));
        List<WeatherForecast> weatherListTo = forecast.get(String.valueOf(flight.getTo()));
        WeatherForecast weatherFrom = weatherListFrom.get(flight.getDeparture());
        WeatherForecast weatherTo = weatherListTo.get(flight.getDeparture() - UTC_MAP.get(flight.getFrom())
                + flight.getDuration() + UTC_MAP.get(flight.getTo()));
        boolean flightWeatherFrom = weatherFrom.getWind() < 30 && weatherFrom.getVisibility() >= 200;
        boolean flightWeatherTo = weatherTo.getWind() < 30 && weatherTo.getVisibility() >= 200;
        if (flightWeatherFrom && flightWeatherTo) {
            return true;
        }
        return false;
    }
}