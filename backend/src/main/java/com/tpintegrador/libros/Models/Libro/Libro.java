package com.tpintegrador.libros.Models.Libro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpintegrador.libros.Models.Genero.Genero;
import com.tpintegrador.libros.Models.Prestamo.Prestamo;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long libro_id;
    @Column(name="titulo")
    private String titulo;
    @Column(name="autor")
    private String autor;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name="libro_genero", //nombre de la tabla de asociacion
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns =  @JoinColumn(name = "genero_id")
    )
    @JsonProperty("generos")
    private List<Genero> generos;
    @Column(name="cantpag")
    private int cantpag;
    @Column(name="sinopsis")
    private String sinopsis;
    @OneToOne(mappedBy = "libro")
    private Prestamo prestamo;

    public Libro(){}
    public Libro(Long libro_id, String titulo, String autor, List<Genero> generos, int cantpag, String sinopsis) {
        this.libro_id = libro_id;
        this.titulo = titulo;
        this.autor = autor;
        this.generos = generos;
        this.cantpag = cantpag;
        this.sinopsis = sinopsis;
    }

    public Long getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(Long libro_id) {
        this.libro_id = libro_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public int getCantpag() {
        return cantpag;
    }

    public void setCantpag(int cantpag) {
        this.cantpag = cantpag;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
