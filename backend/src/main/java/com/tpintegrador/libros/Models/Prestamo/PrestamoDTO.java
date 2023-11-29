package com.tpintegrador.libros.Models.Prestamo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PrestamoDTO {
    //DTO Data Transfer Object
    private Long prestamo_id;
    private Long libro_id;
    private Long usuario_id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date fecha_prestamo;
    private Boolean estado;
    public PrestamoDTO(){}
    public PrestamoDTO(Long prestamo_id, Long libro_id, Long usuario_id, Date fecha_prestamo, Boolean estado) {
        this.prestamo_id = prestamo_id;
        this.libro_id = libro_id;
        this.usuario_id = usuario_id;
        this.fecha_prestamo = fecha_prestamo;
        this.estado = estado;
    }

    public Long getPrestamo_id() {
        return prestamo_id;
    }

    public void setPrestamo_id(Long prestamo_id) {
        this.prestamo_id = prestamo_id;
    }

    public Long getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(Long libro_id) {
        this.libro_id = libro_id;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
