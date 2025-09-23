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
import java.util.Map;

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

    @GetMapping("/enfermedades/ordenadas/severidad")
    public ResponseEntity<List<EnfermedadListDTO>> listarEnfermedadesSeveridad() {
        return ResponseEntity.ok(enfermedadService.listarSeveridad());
    }

    @GetMapping("/enfermedades/ordenadas/nombre-asc")
    public ResponseEntity<List<EnfermedadListDTO>> listarEnfermedadesOrdenadasPorNombreAsc() {
        return ResponseEntity.ok(enfermedadService.ordenarAscendente());
    }

    @GetMapping("/enfermedades/ordenadas/nombre-desc")
    public ResponseEntity<List<EnfermedadListDTO>> listarEnfermedadesOrdenadasPorNombreDesc() {
        return ResponseEntity.ok(enfermedadService.ordenarDescendente());
    }

    @GetMapping("/enfermedades/buscar")
    public ResponseEntity<?> buscarEnfermedades(@RequestParam String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "El parámetro 'nombre' es obligatorio"));
        }

        List<EnfermedadListDTO> lista = enfermedadService.buscarPorNombre(nombre.trim());

        if (lista.isEmpty()) {
            return ResponseEntity.ok(Map.of("mensaje", "No se encontraron resultados"));
        }

        return ResponseEntity.ok(lista);
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

    @GetMapping("/plagas/ordenadas/severidad")
    public ResponseEntity<List<PlagaListDTO>> listarPlagasSeveridad() {
        return ResponseEntity.ok(plagaService.listarSeveridad());
    }

    @GetMapping("/plagas/ordenadas/nombre-asc")
    public ResponseEntity<List<PlagaListDTO>> listarPlagasOrdenadasPorNombreAsc() {
        return ResponseEntity.ok(plagaService.ordenarAscendente());
    }

    @GetMapping("/plagas/ordenadas/nombre-desc")
    public ResponseEntity<List<PlagaListDTO>> listarPlagasOrdenadasPorNombreDesc() {
        return ResponseEntity.ok(plagaService.ordenarDescendente());
    }

    @GetMapping("/plagas/buscar")
    public ResponseEntity<List<PlagaListDTO>> buscarPlagas(@RequestParam String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        List<PlagaListDTO> lista = plagaService.buscarPorNombre(nombre.trim());
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
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