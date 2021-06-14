package com.orangetalents.desafio.service;

import com.orangetalents.desafio.domain.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginService {

    public static Usuario autenticado() {
        try {
            return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
