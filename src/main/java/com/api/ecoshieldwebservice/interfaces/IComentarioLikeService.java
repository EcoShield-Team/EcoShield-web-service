package com.api.ecoshieldwebservice.interfaces;

public interface IComentarioLikeService {
    boolean toggleLike(Long comentarioId, String correo);
    int countLikes(Long comentarioId);
}
