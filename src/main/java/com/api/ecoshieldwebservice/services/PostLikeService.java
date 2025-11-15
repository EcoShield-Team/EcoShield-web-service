package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.entities.Post;
import com.api.ecoshieldwebservice.entities.PostLike;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IPostLikeService;
import com.api.ecoshieldwebservice.repositories.PostLikeRepository;
import com.api.ecoshieldwebservice.repositories.PostRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostLikeService implements IPostLikeService {

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean toggleLike(Long postId, String correo) {
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));

        boolean yaLike = postLikeRepository.existsByPost_PostIdAndUsuario_UsuarioId(postId, usuario.getUsuarioId());

        if (yaLike) {
            postLikeRepository.deleteByPost_PostIdAndUsuario_UsuarioId(postId, usuario.getUsuarioId());
            return false;
        }

        PostLike like = new PostLike();
        like.setPost(post);
        like.setUsuario(usuario);
        postLikeRepository.save(like);

        return true;
    }

    public int countLikes(Long postId) {
        return postLikeRepository.countByPost_PostId(postId);
    }
}
