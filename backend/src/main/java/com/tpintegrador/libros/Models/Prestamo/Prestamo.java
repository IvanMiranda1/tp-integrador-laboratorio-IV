package com.tpintegrador.libros.Models.Prestamo;

import com.tpintegrador.libros.Models.Libro.Libro;
import com.tpintegrador.libros.Models.Usuario.Usuario;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="Prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prestamo_id;
    @OneToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fecha_prestamo")
    private Date fecha_prestamo;
    @Column(name = "estado")
    private Boolean estado;
    public Prestamo(){
        this.estado=false;
    }

    public Prestamo(Long prestamo_id, Libro libro, Usuario usuario, Date fecha_prestamo, Boolean estado) {
        this.prestamo_id = prestamo_id;
        this.libro = libro;
        this.usuario = usuario;
        this.fecha_prestamo = fecha_prestamo;
        this.estado = estado;
    }

    public Long getPrestamo_id() {
        return prestamo_id;
    }

    public void setPrestamo_id(Long prestamo_id) {
        this.prestamo_id = prestamo_id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
