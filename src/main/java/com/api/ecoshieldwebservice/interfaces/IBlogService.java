package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;

import java.util.List;

public interface IBlogService {
    BlogResponseDTO findById(Long id);
    BlogResponseDTO registrar(BlogRequestDTO blogRequestDTO);
    BlogResponseDTO actualizar(Long id, BlogRequestDTO blogRequestDTO);
    void borrar(Long id);
    List<BlogResponseDTO> findAll();
    BlogResponseDTO findTipDelDia();
    List<BlogResponseDTO> findAllNews();
}
