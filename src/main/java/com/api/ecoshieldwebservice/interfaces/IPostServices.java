package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Post;

import java.util.List;

public interface IPostServices {
    public PostResponseDTO registrarPost(PostRequestDTO postRequestDTO);
    public PostResponseDTO actualizarPost(PostRequestDTO postRequestDTO);
    public PostResponseDTO findPostById(Integer id);
    public List<PostResponseDTO> findAllPosts();
    public void borrarPost(Integer id);
}
