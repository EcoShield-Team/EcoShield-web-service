package com.api.ecoshieldwebservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class DeteccionService {
    private final RestTemplate restTemplate;

    @Value("${gemini.apiKey}")
    private String apiKey;

    private static final String MODEL = "gemini-2.5-flash";
    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1";

    public DeteccionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String analyze(String prompt, byte[] imageBytes, String mimeType) {
        Map<String, Object> inlineData = Map.of(
                "mimeType", mimeType,
                "data", Base64.getEncoder().encodeToString(imageBytes)
        );

        Map<String, Object> partText = Map.of("text", prompt);
        Map<String, Object> partImage = Map.of("inlineData", inlineData);
        Map<String, Object> content = Map.of("parts", List.of(partText, partImage));

        Map<String, Object> requestBody = Map.of("contents", List.of(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        String url = String.format("%s/models/%s:generateContent?key=%s", BASE_URL, MODEL, apiKey);

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.POST, request, Map.class);

        Map body = response.getBody();
        if (body == null) return "Respuesta vacía";

        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");
            Map<String, Object> first = candidates != null && !candidates.isEmpty() ? candidates.get(0) : Map.of();
            Map<String, Object> contentMap = (Map<String, Object>) first.get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) contentMap.get("parts");
            return parts.get(0).get("text").toString();
        } catch (Exception e) {
            return "Error procesando respuesta: " + e.getMessage();
        }
    }
}
