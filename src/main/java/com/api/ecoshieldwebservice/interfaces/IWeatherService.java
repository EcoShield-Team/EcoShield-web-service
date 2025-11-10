package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.response.WeatherResponseDTO;

public interface IWeatherService {
    WeatherResponseDTO getWeatherByCity(String city);
    WeatherResponseDTO getWeatherByCoordinates(double lat, double lon);
}
