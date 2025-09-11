package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public PostResponseDTO crearPost(@RequestBody PostRequestDTO postRequestDTO) {
        return postService.registrarPost(postRequestDTO);
    }

    @PutMapping("/post")
    public PostResponseDTO actualizarPost(@RequestBody PostRequestDTO postRequestDTO) {
        return postService.actualizarPost(postRequestDTO);
    }

    @GetMapping("/post")
    public List<PostResponseDTO> findAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/post/{id}")
    public PostResponseDTO findPostById(@PathVariable Integer id) {
        return postService.findPostById(id);
    }

    @DeleteMapping("/post/{id}")
    public void borrarPost(@PathVariable Integer id) {
        postService.borrarPost(id);
    }

}
