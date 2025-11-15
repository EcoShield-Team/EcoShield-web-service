package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.interfaces.IComentarioLikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@Tag(name = "Comentario")
public class ComentarioLikeController {

    @Autowired
    private IComentarioLikeService likeService;

    @Transactional
    @PostMapping("/posts/{postId}/comentarios/{comentarioId}/like")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Boolean> toggleLike(@PathVariable Long postId, @PathVariable Long comentarioId, Authentication auth) {
        boolean estado = likeService.toggleLike(comentarioId, auth.getName());
        return ResponseEntity.ok(estado);
    }

    @GetMapping("/posts/{postId}/comentarios/{comentarioId}/likes")
    public ResponseEntity<Integer> getLikes(@PathVariable Long postId, @PathVariable Long comentarioId) {
        return ResponseEntity.ok(likeService.countLikes(comentarioId));
    }
}
