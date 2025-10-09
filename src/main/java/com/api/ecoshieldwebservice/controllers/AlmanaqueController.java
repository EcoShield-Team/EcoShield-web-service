package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.almanaque.EnfermedadDetailDTO;
import com.api.ecoshieldwebservice.dtos.almanaque.EnfermedadListDTO;
import com.api.ecoshieldwebservice.dtos.almanaque.PlagaDetailDTO;
import com.api.ecoshieldwebservice.dtos.almanaque.PlagaListDTO;
import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.PlagaTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import com.api.ecoshieldwebservice.interfaces.IEnfermedadService;
import com.api.ecoshieldwebservice.interfaces.IPlagaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Almanaque", description = "Catálogo de plagas y enfermedades agrícolas con filtros de búsqueda")
@RestController
@RequestMapping("/almanaque")
@RequiredArgsConstructor
public class AlmanaqueController {

    @Autowired
    private IEnfermedadService enfermedadService;

    @Autowired
    private IPlagaService plagaService;


    @GetMapping("/enfermedades")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> listarEnfermedades() {
        return ResponseEntity.ok(enfermedadService.listarTodas());
    }

    @GetMapping("/enfermedades/ordenadas/severidad")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> listarEnfermedadesSeveridad() {
        return ResponseEntity.ok(enfermedadService.listarSeveridad());
    }

    @GetMapping("/enfermedades/ordenadas/nombre-asc")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> listarEnfermedadesOrdenadasPorNombreAsc() {
        return ResponseEntity.ok(enfermedadService.ordenarAscendente());
    }

    @GetMapping("/enfermedades/ordenadas/nombre-desc")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> listarEnfermedadesOrdenadasPorNombreDesc() {
        return ResponseEntity.ok(enfermedadService.ordenarDescendente());
    }

    @GetMapping("/enfermedades/buscar")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> buscarEnfermedades(@RequestParam String nombre) {
        return ResponseEntity.ok(enfermedadService.buscarPorNombre(nombre));
    }

    @GetMapping("/enfermedades/filtro/tipo")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> filtrarEnfermedadesPorTipo(@RequestParam EnfermedadTipo tipo) {
        return ResponseEntity.ok(enfermedadService.filtrarPorTipo(tipo));
    }

    @GetMapping("/enfermedades/filtro/temporada")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> filtrarEnfermedadesPorTemporada(@RequestParam Temporada temporada) {
        return ResponseEntity.ok(enfermedadService.filtrarPorTemporada(temporada));
    }

    @GetMapping("/enfermedades/filtro/severidad")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> filtrarEnfermedadesPorSeveridad(@RequestParam Severidad severidad) {
        return ResponseEntity.ok(enfermedadService.filtrarPorSeveridad(severidad));
    }

    @GetMapping("/enfermedades/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<EnfermedadDetailDTO> verDetalleEnfermedad(@PathVariable Long id) {
        return ResponseEntity.ok(enfermedadService.verDetalle(id));
    }

    @GetMapping("/enfermedades/{id}/relacionadas")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<EnfermedadListDTO>> enfermedadesRelacionadas(@PathVariable Long id) {
        return ResponseEntity.ok(enfermedadService.enfermedadesRelacionadas(id));
    }


    @GetMapping("/plagas")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> listarPlagas() {
        return ResponseEntity.ok(plagaService.listarTodas());
    }

    @GetMapping("/plagas/ordenadas/severidad")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> listarPlagasSeveridad() {
        return ResponseEntity.ok(plagaService.listarSeveridad());
    }

    @GetMapping("/plagas/ordenadas/nombre-asc")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> listarPlagasOrdenadasPorNombreAsc() {
        return ResponseEntity.ok(plagaService.ordenarAscendente());
    }

    @GetMapping("/plagas/ordenadas/nombre-desc")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> listarPlagasOrdenadasPorNombreDesc() {
        return ResponseEntity.ok(plagaService.ordenarDescendente());
    }

    @GetMapping("/plagas/buscar")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> buscarPlagas(@RequestParam String nombre) {
        return ResponseEntity.ok(plagaService.buscarPorNombre(nombre));
    }

    @GetMapping("/plagas/filtro/tipo")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> filtrarPlagasPorTipo(@RequestParam PlagaTipo tipo) {
        return ResponseEntity.ok(plagaService.filtrarPorTipo(tipo));
    }

    @GetMapping("/plagas/filtro/temporada")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> filtrarPlagasPorTemporada(@RequestParam Temporada temporada) {
        return ResponseEntity.ok(plagaService.filtrarPorTemporada(temporada));
    }

    @GetMapping("/plagas/filtro/severidad")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> filtrarPlagasPorSeveridad(@RequestParam Severidad severidad) {
        return ResponseEntity.ok(plagaService.filtrarPorSeveridad(severidad));
    }

    @GetMapping("/plagas/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PlagaDetailDTO> verDetallePlaga(@PathVariable Long id) {
        return ResponseEntity.ok(plagaService.verDetalle(id));
    }

    @GetMapping("/plagas/{id}/relacionadas")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<PlagaListDTO>> plagasRelacionadas(@PathVariable Long id) {
        return ResponseEntity.ok(plagaService.plagasRelacionadas(id));
    }
}