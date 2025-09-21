package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.PlagaDetailDTO;
import com.api.ecoshieldwebservice.dtos.PlagaListDTO;
import com.api.ecoshieldwebservice.enums.PlagaTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;

import java.util.List;

public interface IPlagaService {
    List<PlagaListDTO> listarTodas();
    List<PlagaListDTO> buscarPorNombre(String nombre);
    List<PlagaListDTO> filtrarPorTipo(PlagaTipo tipo);
    List<PlagaListDTO> filtrarPorTemporada(Temporada temporada);
    List<PlagaListDTO> filtrarPorSeveridad(Severidad severidad);
    PlagaDetailDTO verDetalle(Integer id);
    List<PlagaListDTO> plagasRelacionadas(Integer id);
    List<PlagaListDTO> listarSeveridad();
    List<PlagaListDTO> ordenarAscendente();
    List<PlagaListDTO> ordenarDescendente();
}