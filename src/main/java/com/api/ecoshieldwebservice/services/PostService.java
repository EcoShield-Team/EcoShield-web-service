package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.dtos.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.interfaces.IPostServices;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostServices {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostResponseDTO registrarPost(PostRequestDTO postRequestDTO) {
        Post post = modelMapper.map(postRequestDTO, Post.class);
        post = postRepository.save(post);
        return modelMapper.map(post, PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO actualizarPost(PostRequestDTO postRequestDTO) {
        Post post = modelMapper.map(postRequestDTO, Post.class);
        Post postActualizado = postRepository.save(post);
        return  modelMapper.map(postActualizado, PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO findPostById(Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) { return null; }
        return modelMapper.map(post, PostResponseDTO.class);
    }


    @Override
    public List<PostResponseDTO> findAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void borrarPost(Integer id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        }
    }
}
