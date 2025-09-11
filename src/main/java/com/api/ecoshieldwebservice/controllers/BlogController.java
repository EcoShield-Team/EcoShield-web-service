package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.BlogResponseDTO;
import com.api.ecoshieldwebservice.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs/{id}")
    public BlogResponseDTO findById(@PathVariable Integer id) {
        return blogService.findById(id);
    }

    @PostMapping("/blogs")
    public BlogResponseDTO registrar(@RequestBody BlogRequestDTO blogRequestDTO) {
        return blogService.registrar(blogRequestDTO);
    }

    @PutMapping("/blogs/{id}")
    public BlogResponseDTO actualizar(@PathVariable Integer id, @RequestBody BlogRequestDTO blogRequestDTO) {
        return blogService.actualizar(id, blogRequestDTO);
    }

    @DeleteMapping("/blogs/{id}")
    public void borrar(@PathVariable Integer id) {
        blogService.borrar(id);
    }

    @GetMapping("/blogs")
    public List<BlogResponseDTO> findAll() {
        return blogService.findAll();
    }

    @GetMapping("/blogs/tip")
    public BlogResponseDTO findTipDelDia() { return blogService.findTipDelDia(); }

    @GetMapping("/blogs/news")
    public List<BlogResponseDTO> findAllNews() { return blogService.findAllNews(); }
}
