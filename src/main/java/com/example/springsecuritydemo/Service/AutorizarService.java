package com.example.springsecuritydemo.Service;

import com.example.springsecuritydemo.Modelos.AutorizarModel;
import com.example.springsecuritydemo.Repositorios.IAutorizarRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AutorizarService {
    @Autowired
    private IAutorizarRepositorio repositorio;
    public void guardar(AutorizarModel autorizar)
    {
        this.repositorio.save(autorizar);
    }
}
