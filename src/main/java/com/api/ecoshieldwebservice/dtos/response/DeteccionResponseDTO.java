package com.api.ecoshieldwebservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeteccionResponseDTO {

    private Long deteccionId;
    private String fotoUrl;
    private String descripcion;
    private Double confianza;
    private String tipo;
    private String nombreDetectado;
    private RegionDTO coordenadas;
    private OffsetDateTime fecha;
    private Long fichaId;
    private String tipoFicha;

    private String sintomas;
    private String tratamiento;
    private String causas;
    private String prevencion;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegionDTO {
        private BigDecimal x;
        private BigDecimal y;
        private BigDecimal ancho;
        private BigDecimal alto;
    }
}

