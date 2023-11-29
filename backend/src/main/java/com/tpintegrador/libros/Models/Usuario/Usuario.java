package com.tpintegrador.libros.Models.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpintegrador.libros.Models.Prestamo.Prestamo;
import jakarta.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usuario_id;
    @Column(name="nomyape")
    private String nomyape;
    @Column(name="usuario")
    private String usuario;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario")
    private Prestamo prestamo;
    public Usuario(){}

    public Usuario(Long usuario_id, String nomyape, String usuario, Prestamo prestamo) {
        this.usuario_id = usuario_id;
        this.nomyape = nomyape;
        this.usuario = usuario;
        this.prestamo = prestamo;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNomyape() {
        return nomyape;
    }

    public void setNomyape(String nomyape) {
        this.nomyape = nomyape;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }



}