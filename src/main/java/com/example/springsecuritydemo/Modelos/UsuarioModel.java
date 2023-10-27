package com.example.springsecuritydemo.Modelos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usuarios")//Nombre de nuestra tabla autorizar
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Column(unique = true)//Veridica a travez de SpringSecutity que no se repira tel mismo correo
    private String correo;
    private String telefono;
    private String password;
    private Integer estado;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarios_id")
    private List<AutorizarModel> autorizar;

    public UsuarioModel() {
    }
    //Constructor sin el Integer id
    public UsuarioModel(String nombre, String correo, String telefono, String password, Integer estado, List<AutorizarModel> autorizar) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.estado = estado;
        this.autorizar = autorizar;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<AutorizarModel> getAutorizar() {
        return autorizar;
    }

    public void setAutorizar(List<AutorizarModel> autorizar) {
        this.autorizar = autorizar;
    }
}
