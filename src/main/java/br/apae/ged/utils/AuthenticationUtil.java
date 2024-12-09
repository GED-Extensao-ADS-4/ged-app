package br.apae.ged.utils;

import br.apae.ged.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {

    private AuthenticationUtil(){
        throw new IllegalStateException("Utility Class");
    }

    public static User retriveAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
