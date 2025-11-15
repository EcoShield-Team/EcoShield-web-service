package com.api.ecoshieldwebservice.controllers;

import com.api.ecoshieldwebservice.interfaces.IPostLikeService;
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

@Tag(name = "Post")
@RestController
public class PostLikeController {

    @Autowired
    private IPostLikeService likeService;

    @Transactional
    @PostMapping("/posts/{postId}/like")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Boolean> toggleLike(@PathVariable Long postId, Authentication auth) {
        boolean estado = likeService.toggleLike(postId, auth.getName());
        return ResponseEntity.ok(estado);
    }

    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<Integer> getLikes(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.countLikes(postId));
    }
}
