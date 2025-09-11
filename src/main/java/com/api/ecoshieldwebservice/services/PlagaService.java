package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.PlagaDetailDTO;
import com.api.ecoshieldwebservice.dtos.PlagaListDTO;
import com.api.ecoshieldwebservice.entities.Plaga;
import com.api.ecoshieldwebservice.enums.PlagaTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import com.api.ecoshieldwebservice.interfaces.IPlagaService;
import com.api.ecoshieldwebservice.repositories.PlagaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlagaService implements IPlagaService {

    @Autowired
    private PlagaRepository plagaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PlagaListDTO> listarTodas() {
        return plagaRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> buscarPorNombre(String nombre) {
        return plagaRepository.findByPlaganombreContainingIgnoreCase(nombre)
                .stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> filtrarPorTipo(PlagaTipo tipo) {
        return plagaRepository.findByPlagatipo(tipo)
                .stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> filtrarPorTemporada(Temporada temporada) {
        return plagaRepository.findByTemporada(temporada)
                .stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> filtrarPorSeveridad(Severidad severidad) {
        return plagaRepository.findBySeveridad(severidad)
                .stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlagaDetailDTO verDetalle(Integer id) {
        Plaga plaga = plagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plaga no encontrada"));
        return modelMapper.map(plaga, PlagaDetailDTO.class);
    }

    @Override
    public List<PlagaListDTO> plagasRelacionadas(Integer id) {
        Plaga plaga = plagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plaga no encontrada"));
        return plagaRepository.findRelacionadas(plaga.getPlagatipo(), id)
                .stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }
}