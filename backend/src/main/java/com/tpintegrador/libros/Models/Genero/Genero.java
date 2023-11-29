package com.tpintegrador.libros.Models.Genero;

import com.tpintegrador.libros.Models.Libro.Libro;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genero_id;

    @ManyToMany(mappedBy = "generos")//desde que atributo se hace referencia a mi tabla genero
    private List<Libro> libro;

    @Column(name = "nombre")
    private String nombre;
    public Genero(){}
    public Genero(String nombre){
        this.nombre = nombre;
    }
    public Genero(Long genero_id, List<Libro> libro, String nombre) {
        this.genero_id = genero_id;
        this.libro = libro;
        this.nombre = nombre;
    }

    public Long getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(Long genero_id) {
        this.genero_id = genero_id;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
