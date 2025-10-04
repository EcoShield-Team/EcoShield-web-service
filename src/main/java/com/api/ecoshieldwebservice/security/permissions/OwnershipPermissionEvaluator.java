package com.api.ecoshieldwebservice.security.permissions;

import com.api.ecoshieldwebservice.interfaces.IComentarioService;
import com.api.ecoshieldwebservice.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class OwnershipPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private IPostService postService;

    @Autowired
    private IComentarioService comentarioService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (authentication == null || targetId == null || targetType == null || permission == null) return false;

        String username = authentication.getName();
        String action = String.valueOf(permission).toUpperCase();

        switch (targetType.toUpperCase()) {
            case "POST":
                return switch (action) {
                    case "DELETE", "UPDATE", "READ" -> postService.esAutorDelPost((Long) targetId, username);
                    default -> false;
                };
            case "COMENTARIO":
                return switch (action) {
                    case "DELETE", "UPDATE", "READ" -> comentarioService.esAutorDelComentario((Long) targetId, username);
                    default -> false;
                };
            default:
                return false;
        }
    }
}
