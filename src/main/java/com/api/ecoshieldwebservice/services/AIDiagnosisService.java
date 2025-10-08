package com.api.ecoshieldwebservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class AIDiagnosisService {

    private final RestTemplate restTemplate;

    @Value("${gemini.apiKey}")
    private String apiKey;

    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1";
    private static final String MODEL = "gemini-2.5-flash";

    private static final String BASE_PROMPT = String.join(" ",
            "Eres un ingeniero agrónomo experto en sanidad vegetal.",
            "Analiza la imagen y determina si hay signos de plagas o enfermedades.",
            "Explica en 3-5 líneas: (1) diagnóstico probable, (2) evidencias visuales, (3) recomendación práctica.",
            "Responde en español claro y conciso."
    );

    public AIDiagnosisService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String analyze(MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("No se recibió la parte 'image' o está vacía.");
        }
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Falta configurar gemini.apiKey.");
        }

        String mime = image.getContentType();
        if (mime == null || mime.isBlank()) mime = "image/jpeg";

        Map<String, Object> inlineData = Map.of(
                "mimeType", mime,
                "data", Base64.getEncoder().encodeToString(image.getBytes())
        );

        String fullPrompt = BASE_PROMPT;

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", fullPrompt),
                                Map.of("inlineData", inlineData)
                        ))
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = BASE_URL + "/models/" + MODEL + ":generateContent?key=" + apiKey;

        try {
            ResponseEntity<Map> resp = restTemplate.exchange(
                    url, HttpMethod.POST, new HttpEntity<>(body, headers), Map.class);

            Map<String, Object> respBody = resp.getBody();
            if (respBody == null)
                throw new RuntimeException("Respuesta vacía del modelo.");

            if (respBody.containsKey("error")) {
                throw new RuntimeException("Gemini dijo: " + respBody.get("error"));
            }

            List<Map<String, Object>> candidates =
                    (List<Map<String, Object>>) respBody.get("candidates");
            if (candidates == null || candidates.isEmpty()) {
                throw new RuntimeException("Sin candidatos en la respuesta del modelo.");
            }

            Map<String, Object> content =
                    (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts =
                    (List<Map<String, Object>>) content.get("parts");

            if (parts == null || parts.isEmpty() || parts.get(0).get("text") == null) {
                throw new RuntimeException("Respuesta sin texto utilizable.");
            }

            return parts.get(0).get("text").toString();

        } catch (org.springframework.web.client.HttpClientErrorException e) {
            throw new RuntimeException(
                    "Gemini 4xx: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (org.springframework.web.client.HttpServerErrorException e) {
            throw new RuntimeException(
                    "Gemini 5xx: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (org.springframework.web.client.ResourceAccessException e) {
            throw new RuntimeException("No se pudo conectar a Gemini: " + e.getMessage(), e);
        }
    }

}