package com.tpintegrador.libros.Models.Usuario;

import com.tpintegrador.libros.Models.Prestamo.Prestamo;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

public class UsuarioDTO {
    private Long usuario_id;
    private String nomyape;
    private String usuario;
    private Long prestamo_id;
    public UsuarioDTO(){}
    public UsuarioDTO(Long usuario_id, String nomyape, String usuario, String contrasena, Long prestamo_id) {
        this.usuario_id = usuario_id;
        this.nomyape = nomyape;
        this.usuario = usuario;

        this.prestamo_id = prestamo_id;
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



    public Long getPrestamo_id() {
        return prestamo_id;
    }

    public void setPrestamo_id(Long prestamo_id) {
        this.prestamo_id = prestamo_id;
    }
}
