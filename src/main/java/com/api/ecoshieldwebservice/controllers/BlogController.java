package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.BlogResponseDTO;
import com.api.ecoshieldwebservice.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs/{id}")
    public ResponseEntity<BlogResponseDTO> findById(@PathVariable Integer id) {
        BlogResponseDTO blog = blogService.findById(id);
        if (blog != null) {
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/blogs")
    public ResponseEntity<BlogResponseDTO> registrar(@RequestBody BlogRequestDTO blogRequestDTO) {
        BlogResponseDTO nuevoBlog = blogService.registrar(blogRequestDTO);
        if (nuevoBlog != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBlog);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<BlogResponseDTO> actualizar(@PathVariable Integer id, @RequestBody BlogRequestDTO blogRequestDTO) {
        BlogResponseDTO blogActualizado = blogService.actualizar(id, blogRequestDTO);
        if (blogActualizado != null) {
            return ResponseEntity.ok(blogActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        blogService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogResponseDTO>> findAll() {
        List<BlogResponseDTO> blogs = blogService.findAll();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/blogs/tip")
    public ResponseEntity<BlogResponseDTO> findTipDelDia() {
        BlogResponseDTO tip = blogService.findTipDelDia();
        if (tip == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tip);
    }

    @GetMapping("/blogs/news")
    public ResponseEntity<List<BlogResponseDTO>> findAllNews() {
        List<BlogResponseDTO> news = blogService.findAllNews();
        if (news.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(news);
    }
}
