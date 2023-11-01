package com.example.springsecuritydemo.Repositorios;

import com.example.springsecuritydemo.Modelos.AutorizarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutorizarRepositorio extends JpaRepository<AutorizarModel,Integer> {

}
