package com.example.springsecuritydemo.Modelos;

import javax.persistence.*;

@Entity
@Table(name="autorizar")//Nombre de nuestra tabla autorizar
public class AutorizarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @OneToOne
    @JoinColumn(name = "usuarios_id")
    private UsuarioModel usuarioId;

    public AutorizarModel() {
    }

    public AutorizarModel(String nombre, UsuarioModel usuarioId) {
        this.nombre = nombre;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UsuarioModel getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UsuarioModel usuarioId) {
        this.usuarioId = usuarioId;
    }
}
