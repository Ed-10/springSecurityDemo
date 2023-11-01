package com.example.springsecuritydemo.Service;

import com.example.springsecuritydemo.Modelos.UsuarioModel;
import com.example.springsecuritydemo.Repositorios.IUsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UsuariosService {
    @Autowired
    private IUsuariosRepositorio repositorio;
    public UsuarioModel guardar(UsuarioModel entity)
    {
        return this.repositorio.save(entity);
    }
    //Metodo para buscar por correo
    public UsuarioModel buscarPorCorreo(String correo,Integer estado)
    {
        return this.repositorio.findByCorreoAndEstado(correo,estado);
    }
}
