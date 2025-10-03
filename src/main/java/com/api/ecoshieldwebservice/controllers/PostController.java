package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.dtos.request.PostRequestDTO;
import com.api.ecoshieldwebservice.dtos.PostResponseDTO;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PostResponseDTO crearPost(
            @RequestParam Long usuarioId,
            @RequestParam String postTitulo,
            @RequestParam String postDescripcion,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        PostRequestDTO dto = new PostRequestDTO();
        dto.setUsuarioId(usuarioId);
        dto.setPostTitulo(postTitulo);
        dto.setPostDescripcion(postDescripcion);
        return postService.registrar(dto, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PostResponseDTO actualizarPost(
            @PathVariable Long id,
            @RequestParam Long usuarioId,
            @RequestParam String postTitulo,
            @RequestParam String postDescripcion,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        PostRequestDTO dto = new PostRequestDTO();
        dto.setUsuarioId(usuarioId);
        dto.setPostTitulo(postTitulo);
        dto.setPostDescripcion(postDescripcion);
        return postService.actualizar(id, dto, file);
    }

    @GetMapping("/buscar")
    public List<PostResponseDTO> buscarPorTitulo(@RequestParam String titulo) {
        return postService.findByPosttitulo(titulo);
    }

    @GetMapping
    public List<PostResponseDTO> listarPosts() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostResponseDTO obtenerPost(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void borrarPost(@PathVariable Long id) {
        postService.borrar(id);
    }

    @GetMapping("/usuarios/{usuarioId}/posts")
    public List<PostResponseDTO> listarPorUsuario(@PathVariable Usuario usuarioId) {
        return postService.findByUsuarioid(usuarioId);
    }

    @GetMapping("/{id}/imagen")
    public ResponseEntity<byte[]> verImagen(@PathVariable Long id) {
        byte[] imagen = postService.obtenerImagenPorId(id);

        if (imagen == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"imagen.jpg\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(imagen);
    }
}
