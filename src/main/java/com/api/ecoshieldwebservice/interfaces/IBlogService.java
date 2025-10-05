package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBlogService {
    BlogResponseDTO registrar(BlogRequestDTO dto, MultipartFile imagen, String correo);
    BlogResponseDTO actualizar(Long id, BlogRequestDTO dto, MultipartFile imagen, String correo);
    void borrar(Long id);
    List<BlogResponseDTO> findAll();
    BlogResponseDTO findById(Long id);
    BlogResponseDTO findTipDelDia();
    List<BlogResponseDTO> findAllNews();
}
