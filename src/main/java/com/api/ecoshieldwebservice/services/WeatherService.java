package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.response.WeatherResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IWeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WeatherService implements IWeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public WeatherResponseDTO getWeatherByCity(String city) {
        try {
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String apiUrl = String.format(
                    "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=es&appid=%s", encodedCity, apiKey
            );
            return fetchWeather(apiUrl, null, null);
        } catch (Exception e) {
            return fallbackWeather();
        }
    }

    @Override
    public WeatherResponseDTO getWeatherByCoordinates(double lat, double lon) {
        try {
            String apiUrl = String.format(
                    "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=metric&lang=es&appid=%s", lat, lon, apiKey
            );
            return fetchWeather(apiUrl, lat, lon);
        } catch (Exception e) {
            return fallbackWeather();
        }
    }

    private WeatherResponseDTO fetchWeather(String apiUrl, Double lat, Double lon) {
        URI uri = URI.create(apiUrl);
        String response = restTemplate.getForObject(uri, String.class);
        JSONObject json = new JSONObject(response);

        String location = json.optString("name", "Ubicación desconocida");
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description").toLowerCase();
        double tempMin = json.getJSONObject("main").getDouble("temp_min");
        double tempMax = json.getJSONObject("main").getDouble("temp_max");
        int clouds = json.getJSONObject("clouds").getInt("all");
        String iconCode = json.getJSONArray("weather").getJSONObject(0).getString("icon");

        boolean hasRain = json.has("rain") || description.contains("lluvia") || iconCode.startsWith("09") || iconCode.startsWith("10");
        boolean hasSnow = json.has("snow") || description.contains("nieve") || iconCode.startsWith("13");

        String intensityText = "";
        if (json.has("rain")) {
            JSONObject rain = json.getJSONObject("rain");
            double mm = rain.has("1h") ? rain.getDouble("1h") : (rain.has("3h") ? rain.getDouble("3h") / 3 : 0);
            if (mm > 0) {
                if (mm < 2) intensityText = "Lluvia ligera (" + mm + " mm/h)";
                else if (mm < 10) intensityText = "Lluvia moderada (" + mm + " mm/h)";
                else intensityText = "Lluvia intensa (" + mm + " mm/h)";
            }
        }

        String rainProb;
        if (hasRain) {rainProb = "Prob. lluvia alta";} else if (clouds < 30) {rainProb = "Prob. lluvia baja";
        } else if (clouds < 70) {rainProb = "Prob. lluvia media";} else {rainProb = "Prob. lluvia alta";}

        String weatherIcon = mapWeatherIconClass(iconCode);
        String precipIcon = mapPrecipIconClass(hasRain, hasSnow);

        if (lat != null && lon != null) {
            try {
                String geoUrl = String.format(
                        "https://api.openweathermap.org/geo/1.0/reverse?lat=%f&lon=%f&limit=1&appid=%s", lat, lon, apiKey);

                String geoResponse = restTemplate.getForObject(URI.create(geoUrl), String.class);

                if (geoResponse != null && !geoResponse.isEmpty()) {
                    org.json.JSONArray geoArray = new org.json.JSONArray(geoResponse);
                    if (!geoArray.isEmpty()) {
                        JSONObject geo = geoArray.getJSONObject(0);
                        String district = geo.optString("name", location);
                        String country = geo.optString("country", "PE");
                        location = district + " - " + location + " (" + country + ")";
                    }
                }

            } catch (Exception e) {
                System.err.println("Error al obtener nombre de distrito: " + e.getMessage());
            }
        }

        if (!intensityText.isEmpty()) {description = description + " — " + intensityText;}

        return new WeatherResponseDTO(location, description, tempMin, tempMax, rainProb, weatherIcon, precipIcon);
    }

    private String mapWeatherIconClass(String code) {
        if (code.startsWith("01")) return "fa-solid fa-sun";
        if (code.startsWith("02")) return "fa-solid fa-cloud-sun";
        if (code.startsWith("03") || code.startsWith("04")) return "fa-solid fa-cloud";
        if (code.startsWith("09") || code.startsWith("10")) return "fa-solid fa-cloud-rain";
        if (code.startsWith("11")) return "fa-solid fa-bolt";
        if (code.startsWith("13")) return "fa-solid fa-snowflake";
        if (code.startsWith("50")) return "fa-solid fa-smog";
        return "fa-solid fa-circle-question";
    }

    private String mapPrecipIconClass(boolean hasRain, boolean hasSnow) {
        if (hasRain) return "fa-solid fa-droplet";
        if (hasSnow) return "fa-solid fa-snowflake";
        return "fa-solid fa-ban";
    }

    private WeatherResponseDTO fallbackWeather() {
        return new WeatherResponseDTO("Desconocido", "No disponible", 0.0, 0.0, "Sin datos", "fa-solid fa-circle-question", "fa-solid fa-ban");
    }
}
