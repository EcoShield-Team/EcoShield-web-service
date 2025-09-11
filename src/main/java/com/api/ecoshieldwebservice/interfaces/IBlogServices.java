package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.BlogResponseDTO;

import java.util.List;

public interface IBlogServices {
    BlogResponseDTO findById(Integer id);
    BlogResponseDTO registrar(BlogRequestDTO blogRequestDTO);
    BlogResponseDTO actualizar(Integer id, BlogRequestDTO blogRequestDTO);
    void borrar(Integer id);
    List<BlogResponseDTO> findAll();
    BlogResponseDTO findTipDelDia();
    List<BlogResponseDTO> findAllNews();
}
