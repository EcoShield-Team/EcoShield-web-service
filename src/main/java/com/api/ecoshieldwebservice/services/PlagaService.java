package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.almanaque.PlagaDetailDTO;
import com.api.ecoshieldwebservice.dtos.almanaque.PlagaListDTO;
import com.api.ecoshieldwebservice.entities.Plaga;
import com.api.ecoshieldwebservice.enums.PlagaTipo;
import com.api.ecoshieldwebservice.enums.Severidad;
import com.api.ecoshieldwebservice.enums.Temporada;
import com.api.ecoshieldwebservice.interfaces.IPlagaService;
import com.api.ecoshieldwebservice.repositories.PlagaRepository;
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
public class PlagaService implements IPlagaService {

    @Autowired
    private PlagaRepository plagaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PlagaListDTO> listarTodas() {
        List<Plaga> lista = plagaRepository.findAll();
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay plagas disponibles");
        }
        return lista.stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El parámetro 'nombre' es obligatorio");
        }
        List<Plaga> lista = plagaRepository.findByPlagaNombreContainingIgnoreCase(nombre.trim());
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron resultados");
        }
        return lista.stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> filtrarPorTipo(PlagaTipo tipo) {
        List<Plaga> lista = plagaRepository.findByPlagaTipo(tipo);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron plagas de ese tipo");
        }
        return lista.stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> filtrarPorTemporada(Temporada temporada) {
        List<Plaga> lista = plagaRepository.findByTemporada(temporada);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron plagas en esa temporada");
        }
        return lista.stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> filtrarPorSeveridad(Severidad severidad) {
        List<Plaga> lista = plagaRepository.findBySeveridad(severidad);
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontraron plagas con esa severidad");
        }
        return lista.stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlagaDetailDTO verDetalle(Long id) {
        Plaga plaga = plagaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plaga no encontrada"));
        return modelMapper.map(plaga, PlagaDetailDTO.class);
    }

    @Override
    public List<PlagaListDTO> plagasRelacionadas(Long id) {
        Plaga plaga = plagaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plaga no encontrada"));
        List<Plaga> relacionadas = plagaRepository.findRelacionadas(plaga.getPlagaTipo(), id);
        if (relacionadas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay plagas relacionadas");
        }
        return relacionadas.stream()
                .limit(4)
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> listarSeveridad() {
        List<Plaga> lista = plagaRepository.findAllOrderBySeveridad();
        if (lista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay plagas disponibles");
        }
        return lista.stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> ordenarAscendente() {
        return plagaRepository.findAllByOrderByPlagaNombreAsc()
                .stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlagaListDTO> ordenarDescendente() {
        return plagaRepository.findAllByOrderByPlagaNombreDesc()
                .stream()
                .map(p -> modelMapper.map(p, PlagaListDTO.class))
                .collect(Collectors.toList());
    }
}