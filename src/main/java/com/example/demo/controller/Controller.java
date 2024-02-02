package com.example.demo.controller;

import com.example.demo.model.FlightStatus;
import com.example.demo.service.FlightStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightsforecast")
@RequiredArgsConstructor
public class Controller {
    private final FlightStatusService flightStatusService;

    @PostMapping("/flightstatus")
    public void getJson(@RequestBody FlightStatus flightStatus) {
        flightStatusService.getFlightStatus(flightStatus);
    }
}