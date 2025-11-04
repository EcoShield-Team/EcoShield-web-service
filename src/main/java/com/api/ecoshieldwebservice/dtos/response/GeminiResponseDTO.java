package com.api.ecoshieldwebservice.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiResponseDTO {

    private String nombre;

    @JsonProperty("nombre_cientifico")
    private String nombreCientifico;

    private String tipo;

    @JsonProperty("tipo_plaga")
    private String tipoPlaga;

    private String severidad;

    private String temporada;

    private Double confianza;
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal ancho;
    private BigDecimal alto;

    private String descripcion;
    private String texto;

    private String sintomas;
    private String tratamiento;
    private String causas;
    private String prevencion;

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
                mapper.findAndRegisterModules();

                GeminiResponseDTO parsed = mapper.readValue(jsonCandidate, GeminiResponseDTO.class);

                result.setNombre(parsed.getNombre());
                result.setNombreCientifico(parsed.getNombreCientifico());
                result.setTipo(parsed.getTipo());
                result.setTipoPlaga(parsed.getTipoPlaga());
                result.setSeveridad(parsed.getSeveridad());
                result.setTemporada(parsed.getTemporada());
                result.setConfianza(parsed.getConfianza());
                result.setX(parsed.getX());
                result.setY(parsed.getY());
                result.setAncho(parsed.getAncho());
                result.setAlto(parsed.getAlto());
                result.setSintomas(parsed.getSintomas());
                result.setTratamiento(parsed.getTratamiento());
                result.setCausas(parsed.getCausas());
                result.setPrevencion(parsed.getPrevencion());
            } else {
                System.err.println("No se encontró un bloque JSON válido en el texto de Gemini.");
            }

        } catch (Exception e) {
            System.err.println("Error al parsear JSON de Gemini: " + e.getMessage());
        }

        System.out.println("JSON parseado correctamente: " + result);
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "GeminiResponseDTO[nombre='%s', tipo='%s', severidad='%s', temporada='%s', confianza=%s]",
                nombre, tipo, severidad, temporada, confianza
        );
    }
}
