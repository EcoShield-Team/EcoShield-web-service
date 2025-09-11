package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.EnfermedadDetailDTO;
import com.api.ecoshieldwebservice.dtos.EnfermedadListDTO;
import com.api.ecoshieldwebservice.dtos.PlagaDetailDTO;
import com.api.ecoshieldwebservice.dtos.PlagaListDTO;
import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.PlagaTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import com.api.ecoshieldwebservice.interfaces.IEnfermedadService;
import com.api.ecoshieldwebservice.interfaces.IPlagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almanaque")
@RequiredArgsConstructor
public class AlmanaqueController {

    @Autowired
    private IEnfermedadService enfermedadService;

    @Autowired
    private IPlagaService plagaService;



    @GetMapping("/enfermedades")
    public ResponseEntity<List<EnfermedadListDTO>> listarEnfermedades() {
        return ResponseEntity.ok(enfermedadService.listarTodas());
    }

    @GetMapping("/enfermedades/buscar")
    public ResponseEntity<List<EnfermedadListDTO>> buscarEnfermedades(@RequestParam String nombre) {
        return ResponseEntity.ok(enfermedadService.buscarPorNombre(nombre));
    }

    @GetMapping("/enfermedades/filtro/tipo")
    public ResponseEntity<List<EnfermedadListDTO>> filtrarEnfermedadesPorTipo(@RequestParam EnfermedadTipo tipo) {
        return ResponseEntity.ok(enfermedadService.filtrarPorTipo(tipo));
    }

    @GetMapping("/enfermedades/filtro/temporada")
    public ResponseEntity<List<EnfermedadListDTO>> filtrarEnfermedadesPorTemporada(@RequestParam Temporada temporada) {
        return ResponseEntity.ok(enfermedadService.filtrarPorTemporada(temporada));
    }

    @GetMapping("/enfermedades/filtro/severidad")
    public ResponseEntity<List<EnfermedadListDTO>> filtrarEnfermedadesPorSeveridad(@RequestParam Severidad severidad) {
        return ResponseEntity.ok(enfermedadService.filtrarPorSeveridad(severidad));
    }

    @GetMapping("/enfermedades/{id}")
    public ResponseEntity<EnfermedadDetailDTO> verDetalleEnfermedad(@PathVariable Integer id) {
        return ResponseEntity.ok(enfermedadService.verDetalle(id));
    }

    @GetMapping("/enfermedades/{id}/relacionadas")
    public ResponseEntity<List<EnfermedadListDTO>> enfermedadesRelacionadas(@PathVariable Integer id) {
        return ResponseEntity.ok(enfermedadService.enfermedadesRelacionadas(id));
    }



    @GetMapping("/plagas")
    public ResponseEntity<List<PlagaListDTO>> listarPlagas() {
        return ResponseEntity.ok(plagaService.listarTodas());
    }

    @GetMapping("/plagas/buscar")
    public ResponseEntity<List<PlagaListDTO>> buscarPlagas(@RequestParam String nombre) {
        return ResponseEntity.ok(plagaService.buscarPorNombre(nombre));
    }

    @GetMapping("/plagas/filtro/tipo")
    public ResponseEntity<List<PlagaListDTO>> filtrarPlagasPorTipo(@RequestParam PlagaTipo tipo) {
        return ResponseEntity.ok(plagaService.filtrarPorTipo(tipo));
    }

    @GetMapping("/plagas/filtro/temporada")
    public ResponseEntity<List<PlagaListDTO>> filtrarPlagasPorTemporada(@RequestParam Temporada temporada) {
        return ResponseEntity.ok(plagaService.filtrarPorTemporada(temporada));
    }

    @GetMapping("/plagas/filtro/severidad")
    public ResponseEntity<List<PlagaListDTO>> filtrarPlagasPorSeveridad(@RequestParam Severidad severidad) {
        return ResponseEntity.ok(plagaService.filtrarPorSeveridad(severidad));
    }

    @GetMapping("/plagas/{id}")
    public ResponseEntity<PlagaDetailDTO> verDetallePlaga(@PathVariable Integer id) {
        return ResponseEntity.ok(plagaService.verDetalle(id));
    }

    @GetMapping("/plagas/{id}/relacionadas")
    public ResponseEntity<List<PlagaListDTO>> plagasRelacionadas(@PathVariable Integer id) {
        return ResponseEntity.ok(plagaService.plagasRelacionadas(id));
    }
}