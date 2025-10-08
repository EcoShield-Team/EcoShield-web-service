package com.api.ecoshieldwebservice.controllers;
import com.api.ecoshieldwebservice.services.AIDiagnosisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/deteccion")
@RequiredArgsConstructor
public class DeteccionController {

    private final AIDiagnosisService diagnosisService;

    @Operation(summary = "Analiza una imagen de cultivo (sin prompt)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Diagnóstico generado",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping(
            value = "/analyze",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> analyze(
            @RequestPart("image") MultipartFile image) throws IOException {

        String result = diagnosisService.analyze(image);
        return ResponseEntity.ok(result);
    }
}