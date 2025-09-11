package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.ComentarioRequestDTO;
import com.api.ecoshieldwebservice.dtos.ComentarioResponseDTO;
import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.interfaces.IComentarioServices;
import com.api.ecoshieldwebservice.repositories.ComentarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService implements IComentarioServices {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ComentarioResponseDTO registrarComentario(ComentarioRequestDTO comentarioRequestDTO) {
        Comentario comentario =  modelMapper.map(comentarioRequestDTO, Comentario.class);
        comentario =  comentarioRepository.save(comentario);
        return modelMapper.map(comentario, ComentarioResponseDTO.class);
    }

    @Override
    public ComentarioResponseDTO actualizarComentario(ComentarioRequestDTO comentarioRequestDTO) {
        Comentario comentario =  modelMapper.map(comentarioRequestDTO, Comentario.class);
        Comentario comentarioActualizado = comentarioRepository.save(comentario);
        return modelMapper.map(comentarioActualizado, ComentarioResponseDTO.class);
    }

    @Override
    public ComentarioResponseDTO findById(Integer id) {
        return comentarioRepository.findById(id)
                .map(comentario -> modelMapper.map(comentario, ComentarioResponseDTO.class))
                .orElse(null);
    }

    @Override
    public List<ComentarioResponseDTO> findAll() {
        return comentarioRepository.findAll()
                .stream()
                .map(comentario  -> modelMapper.map(comentario, ComentarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void borrarComentario(Integer id) {
        if (comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
        }
    }
}
