package com.example.springsecuritydemo.seguridad;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Primary
public class PermisosServices {
    public boolean comprobacionDeRoles(String rol){
        SecurityContext context= SecurityContextHolder.getContext();
        if (context==null){
            return false;
        }
        Authentication auth = context.getAuthentication();
        if (auth==null){
            return false;
        }
        Collection <? extends GrantedAuthority> authorities = auth.getAuthorities(); //getAuthorities retorna todos lo ROLES y los guardamos dentro de una coleccion
        return authorities.contains(new SimpleGrantedAuthority(rol));
    }
}
