package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IBlogServices;
import com.api.ecoshieldwebservice.services.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogServices blogService;


    @GetMapping("/{id}")
    public ResponseEntity<BlogResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BlogResponseDTO> registrar(@Valid @RequestBody BlogRequestDTO blogRequestDTO) {
        BlogResponseDTO nuevoBlog = blogService.registrar(blogRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBlog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDTO> actualizar(@PathVariable Long id,
                                                      @Valid @RequestBody BlogRequestDTO blogRequestDTO) {
        return ResponseEntity.ok(blogService.actualizar(id, blogRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        blogService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BlogResponseDTO>> findAll() {
        return ResponseEntity.ok(blogService.findAll());
    }

    @GetMapping("/tip")
    public ResponseEntity<BlogResponseDTO> findTipDelDia() {
        return ResponseEntity.ok(blogService.findTipDelDia());
    }

    @GetMapping("/news")
    public ResponseEntity<List<BlogResponseDTO>> findAllNews() {
        return ResponseEntity.ok(blogService.findAllNews());
    }
}
