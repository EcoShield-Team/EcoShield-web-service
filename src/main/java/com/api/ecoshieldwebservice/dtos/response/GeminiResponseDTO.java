package com.api.ecoshieldwebservice.dtos.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeminiResponseDTO {
    private String nombre;
    private String tipo;
    private Double confianza;
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal ancho;
    private BigDecimal alto;
    private String descripcion;
    private String texto;

    public static GeminiResponseDTO fromJson(String rawText) {
        GeminiResponseDTO result = new GeminiResponseDTO();

        try {
            String cleanText = rawText
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();
            result.setTexto(cleanText);

            int startJson = cleanText.indexOf("{");
            if (startJson > 0) {
                String descripcion = cleanText.substring(0, startJson).trim();
                result.setDescripcion(descripcion);
            }

            Pattern jsonPattern = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL);
            Matcher matcher = jsonPattern.matcher(cleanText);

            if (matcher.find()) {
                String jsonCandidate = matcher.group(0);

                ObjectMapper mapper = new ObjectMapper();
                GeminiResponseDTO parsed = mapper.readValue(jsonCandidate, GeminiResponseDTO.class);

                result.setNombre(parsed.getNombre());
                result.setTipo(parsed.getTipo());
                result.setConfianza(parsed.getConfianza());
                result.setX(parsed.getX());
                result.setY(parsed.getY());
                result.setAncho(parsed.getAncho());
                result.setAlto(parsed.getAlto());
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error al parsear JSON de Gemini: " + e.getMessage());
        }

        return result;
    }
}