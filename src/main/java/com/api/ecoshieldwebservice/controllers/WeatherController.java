package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.response.WeatherResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IWeatherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Clima", description = "Obtiene el estado del clima actual según la ciudad o coordenadas geográficas")
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private IWeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherResponseDTO> getWeather(@RequestParam(required = false) String city, @RequestParam(required = false) Double lat, @RequestParam(required = false) Double lon) {
        WeatherResponseDTO response;

        if (lat != null && lon != null) {
            response = weatherService.getWeatherByCoordinates(lat, lon);
        } else if (city != null && !city.isBlank()) {
            response = weatherService.getWeatherByCity(city);
        } else {
            response = weatherService.getWeatherByCity("Lima");
        }

        return ResponseEntity.ok(response);
    }
}
