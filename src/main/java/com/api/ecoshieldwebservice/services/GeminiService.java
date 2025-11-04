package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.response.GeminiResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IGeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GeminiService implements IGeminiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gemini.apiKey}")
    private String apiKey;

    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1";
    private static final String MODEL = "gemini-2.5-flash";

    @Override
    public GeminiResponseDTO analyze(MultipartFile image) {
        try {

            if (image == null || image.isEmpty()) {
                throw new IllegalArgumentException("Debe enviar una imagen.");
            }

            String mime = Optional.ofNullable(image.getContentType()).orElse("image/jpeg");

            Map<String, Object> inlineData = Map.of(
                    "mimeType", mime,
                    "data", Base64.getEncoder().encodeToString(image.getBytes())
            );

            String prompt = String.join(" ",
                    "Analiza la imagen de una planta, hoja o cultivo.",
                    "Identifica si presenta una plaga o enfermedad agrícola (responde en español).",
                    "Devuelve una breve descripción (1 a 2 líneas) seguida estrictamente de un JSON plano con esta estructura:",
                    "{",
                    "\"nombre\": \"<nombre detectado>\",",
                    "\"nombre_cientifico\": \"<nombre científico si se conoce>\",",
                    "\"tipo\": \"PLAGA o ENFERMEDAD\",",
                    "\"tipo_plaga\": \"<tipo general: insecto, ácaro, nemátodo, hongo, bacteria, virus, etc.>\",",
                    "\"severidad\": \"<leve, moderada o severa>\",",
                    "\"temporada\": \"<época o condición del año en que ocurre>\",",
                    "\"confianza\": <número entre 0 y 1>,",
                    "\"x\": <coordenadaX>,",
                    "\"y\": <coordenadaY>,",
                    "\"ancho\": <valor>,",
                    "\"alto\": <valor>,",
                    "\"sintomas\": \"<síntomas visibles>\",",
                    "\"tratamiento\": \"<tratamiento recomendado>\",",
                    "\"causas\": \"<principales causas>\",",
                    "\"prevencion\": \"<medidas de prevención>\"",
                    "}"
            );

            Map<String, Object> body = Map.of(
                    "contents", List.of(
                            Map.of("parts", List.of(
                                    Map.of("text", prompt),
                                    Map.of("inlineData", inlineData)
                            ))
                    )
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String url = BASE_URL + "/models/" + MODEL + ":generateContent?key=" + apiKey;

            ResponseEntity<Map> resp = restTemplate.exchange(
                    url, HttpMethod.POST, new HttpEntity<>(body, headers), Map.class
            );

            Map<String, Object> respBody = resp.getBody();
            if (respBody == null || !respBody.containsKey("candidates"))
                throw new RuntimeException("Respuesta inválida del modelo.");

            List<Map<String, Object>> candidates = (List<Map<String, Object>>) respBody.get("candidates");
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

            String text = parts.get(0).get("text").toString();

            System.out.println("Respuesta Gemini:\n" + text); //quitar luego

            return GeminiResponseDTO.fromJson(text);

        } catch (Exception e) {
            throw new RuntimeException("Error al analizar la imagen con Gemini: " + e.getMessage(), e);
        }
    }

}