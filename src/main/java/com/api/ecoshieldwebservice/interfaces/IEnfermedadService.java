package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.almanaque.EnfermedadDetailDTO;
import com.api.ecoshieldwebservice.dtos.almanaque.EnfermedadListDTO;
import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;

import java.util.List;

public interface IEnfermedadService {
    List<EnfermedadListDTO> listarTodas();
    List<EnfermedadListDTO> buscarPorNombre(String nombre);
    List<EnfermedadListDTO> filtrarPorTipo(EnfermedadTipo tipo);
    List<EnfermedadListDTO> filtrarPorTemporada(Temporada temporada);
    List<EnfermedadListDTO> filtrarPorSeveridad(Severidad severidad);
    EnfermedadDetailDTO verDetalle(Long id);
    List<EnfermedadListDTO> enfermedadesRelacionadas(Long id);
    List<EnfermedadListDTO> listarSeveridad();
    List<EnfermedadListDTO> ordenarAscendente();
    List<EnfermedadListDTO> ordenarDescendente();
}
