package com.example.springsecuritydemo.Repositorios;

import com.example.springsecuritydemo.Modelos.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuariosRepositorio extends JpaRepository<UsuarioModel, Integer> {
    //Metodo para ir a buscar un usuario a atraves de un correo
    //select " from usuarios where correo= 'user@user.com' and estado=1
    public UsuarioModel findByCorreoAndEstado(String correo, Integer estado);
}
