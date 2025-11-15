package com.api.ecoshieldwebservice.interfaces;

public interface IPostLikeService {
    boolean toggleLike(Long postId, String correo);
    int countLikes(Long postId);
}
