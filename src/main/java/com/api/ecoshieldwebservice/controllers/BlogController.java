package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.BlogRequestDTO;
import com.api.ecoshieldwebservice.dtos.response.BlogResponseDTO;
import com.api.ecoshieldwebservice.interfaces.IBlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlogResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlogResponseDTO> registrar(@Valid @RequestBody BlogRequestDTO blogRequestDTO) {
        BlogResponseDTO nuevoBlog = blogService.registrar(blogRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBlog);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BlogResponseDTO> actualizar(@PathVariable Long id,
                                                      @Valid @RequestBody BlogRequestDTO blogRequestDTO) {
        return ResponseEntity.ok(blogService.actualizar(id, blogRequestDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        blogService.borrar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<BlogResponseDTO>> findAll() {
        return ResponseEntity.ok(blogService.findAll());
    }

    @GetMapping("/tip")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<BlogResponseDTO> findTipDelDia() {
        return ResponseEntity.ok(blogService.findTipDelDia());
    }

    @GetMapping("/news")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<BlogResponseDTO>> findAllNews() {
        return ResponseEntity.ok(blogService.findAllNews());
    }
}
