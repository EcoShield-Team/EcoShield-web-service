package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;

import java.util.List;

public interface IBlogService {
    BlogResponseDTO registrar(BlogRequestDTO dto, String correo);
    BlogResponseDTO actualizar(Long id, BlogRequestDTO dto, String correo);
    void borrar(Long id);
    List<BlogResponseDTO> findAll();
    BlogResponseDTO findById(Long id);
    BlogResponseDTO findTipDelDia();
    List<BlogResponseDTO> findAllNews();
}
