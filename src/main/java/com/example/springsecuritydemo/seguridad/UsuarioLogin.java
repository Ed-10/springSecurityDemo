package com.example.springsecuritydemo.seguridad;

import com.example.springsecuritydemo.Modelos.AutorizarModel;
import com.example.springsecuritydemo.Modelos.UsuarioModel;
import com.example.springsecuritydemo.Service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioLogin implements UserDetailsService {
    @Autowired
    private UsuariosService usuariosService;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UsuarioModel usuario = this.usuariosService.buscarPorCorreo(username,1);//En la BD si es uno se puede logear
        if (usuario==null)
        {
            throw new UsernameNotFoundException("El E-mail: " + username + "No exisite");
        }
        //Configuracion de los autorities
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (AutorizarModel rol:usuario.getAutorizar()){
            authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        System.out.println(authorities);
        if (authorities.isEmpty())//Si esta vacio
        {
            throw new UsernameNotFoundException("Error en el login: E-MAIL " + username + "No tiene roles asignados");
        }
        return new User(usuario.getNombre(), usuario.getPassword(),true,true,true,true,authorities);
    }
}
