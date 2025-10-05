package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.almanaque.EnfermedadDetailDTO;
import com.api.ecoshieldwebservice.dtos.almanaque.EnfermedadListDTO;
import com.api.ecoshieldwebservice.entities.Enfermedad;
import com.api.ecoshieldwebservice.enums.EnfermedadTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import com.api.ecoshieldwebservice.interfaces.IEnfermedadService;
import com.api.ecoshieldwebservice.repositories.EnfermedadRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        List<Enfermedad> lista = enfermedadRepository.findAll();
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay enfermedades disponibles");
        }
        return lista.stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El parámetro 'nombre' es obligatorio");
        }
        List<Enfermedad> lista = enfermedadRepository.findByEnfermedadNombreContainingIgnoreCase(nombre.trim());
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron resultados");
        }
        return lista.stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> filtrarPorTipo(EnfermedadTipo tipo) {
        List<Enfermedad> lista = enfermedadRepository.findByEnfermedadTipo(tipo);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron enfermedades de ese tipo");
        }
        return lista.stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> filtrarPorTemporada(Temporada temporada) {
        List<Enfermedad> lista = enfermedadRepository.findByTemporada(temporada);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron enfermedades en esa temporada");
        }
        return lista.stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> filtrarPorSeveridad(Severidad severidad) {
        List<Enfermedad> lista = enfermedadRepository.findBySeveridad(severidad);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron enfermedades con esa severidad");
        }
        return lista.stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnfermedadDetailDTO verDetalle(Long id) {
        Enfermedad enfermedad = enfermedadRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enfermedad no encontrada"));
        return modelMapper.map(enfermedad, EnfermedadDetailDTO.class);
    }

    @Override
    public List<EnfermedadListDTO> enfermedadesRelacionadas(Long id) {
        Enfermedad enfermedad = enfermedadRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enfermedad no encontrada"));
        List<Enfermedad> relacionadas = enfermedadRepository.findRelacionadas(enfermedad.getEnfermedadTipo(), id);
        if (relacionadas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay enfermedades relacionadas");
        }
        return relacionadas.stream()
                .limit(4)
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> listarSeveridad() {
        List<Enfermedad> lista = enfermedadRepository.findAllOrderBySeveridad();
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay enfermedades disponibles");
        }
        return lista.stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> ordenarAscendente() {
        return enfermedadRepository.findAllByOrderByEnfermedadNombreAsc()
                .stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnfermedadListDTO> ordenarDescendente() {
        return enfermedadRepository.findAllByOrderByEnfermedadNombreDesc()
                .stream()
                .map(e -> modelMapper.map(e, EnfermedadListDTO.class))
                .collect(Collectors.toList());
    }
}
