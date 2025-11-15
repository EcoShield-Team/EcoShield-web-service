package com.api.ecoshieldwebservice.services;

import com.api.ecoshieldwebservice.entities.Comentario;
import com.api.ecoshieldwebservice.entities.ComentarioLike;
import com.api.ecoshieldwebservice.entities.Usuario;
import com.api.ecoshieldwebservice.interfaces.IComentarioLikeService;
import com.api.ecoshieldwebservice.repositories.ComentarioLikeRepository;
import com.api.ecoshieldwebservice.repositories.ComentarioRepository;
import com.api.ecoshieldwebservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ComentarioLikeService implements IComentarioLikeService {

    @Autowired
    private ComentarioLikeRepository comentarioLikeRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean toggleLike(Long comentarioId, String correo) {
        Usuario usuario = usuarioRepository.findByUsuarioCorreo(correo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario no encontrado"));

        boolean yaLike = comentarioLikeRepository.existsByComentario_ComentarioIdAndUsuario_UsuarioId(comentarioId, usuario.getUsuarioId());

        if (yaLike) {
            comentarioLikeRepository.deleteByComentario_ComentarioIdAndUsuario_UsuarioId(comentarioId, usuario.getUsuarioId());
            return false;
        }

        ComentarioLike like = new ComentarioLike();
        like.setComentario(comentario);
        like.setUsuario(usuario);
        comentarioLikeRepository.save(like);

        return true;
    }

    public int countLikes(Long comentarioId) {
        return comentarioLikeRepository.countByComentario_ComentarioId(comentarioId);
    }
}
