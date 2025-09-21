package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.EnfermedadDetailDTO;
import com.api.ecoshieldwebservice.dtos.EnfermedadListDTO;
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
    EnfermedadDetailDTO verDetalle(Integer id);
    List<EnfermedadListDTO> enfermedadesRelacionadas(Integer id);
    List<EnfermedadListDTO> listarSeveridad();
    List<EnfermedadListDTO> ordenarAscendente();
    List<EnfermedadListDTO> ordenarDescendente();
}
