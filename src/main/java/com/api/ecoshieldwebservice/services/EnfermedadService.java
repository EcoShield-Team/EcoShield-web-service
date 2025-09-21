package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.EnfermedadDetailDTO;
import com.api.ecoshieldwebservice.dtos.EnfermedadListDTO;
import com.api.ecoshieldwebservice.entities.Enfermedad;
import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import com.api.ecoshieldwebservice.interfaces.IEnfermedadService;
import com.api.ecoshieldwebservice.repositories.EnfermedadRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnfermedadService implements IEnfermedadService {

    @Autowired
    private EnfermedadRepository enfermedadRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EnfermedadListDTO> listarTodas() {
        return enfermedadRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> buscarPorNombre(String nombre) {
        return enfermedadRepository.findByEnfermedadnombreContainingIgnoreCase(nombre)
                .stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> filtrarPorTipo(EnfermedadTipo tipo) {
        return enfermedadRepository.findByEnfermedadtipo(tipo)
                .stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> filtrarPorTemporada(Temporada temporada) {
        return enfermedadRepository.findByTemporada(temporada)
                .stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> filtrarPorSeveridad(Severidad severidad) {
        return enfermedadRepository.findBySeveridad(severidad)
                .stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnfermedadDetailDTO verDetalle(Integer id) {
        Enfermedad enfermedad = enfermedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enfermedad no encontrada"));
        return modelMapper.map(enfermedad, EnfermedadDetailDTO.class);
    }

    @Override
    public List<EnfermedadListDTO> enfermedadesRelacionadas(Integer id) {
        Enfermedad enfermedad = enfermedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enfermedad no encontrada"));
        return enfermedadRepository.findRelacionadas(enfermedad.getEnfermedadtipo(), id)
                .stream()
                .limit(4)
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }
}
