package com.tpintegrador.libros.Models.Libro;

import java.util.List;

public class LibroP {
    private Long libro_id;
    private String titulo;
    private String autor;
    private List<Long> generos_id;
    private int cantpag;
    private String sinopsis;
    public LibroP(){}
    public LibroP(String titulo, String autor, List<Long> generos_id, int cantpag, String sinopsis) {
        this.titulo = titulo;
        this.autor = autor;
        this.generos_id = generos_id;
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

    public List<Long> getGeneros_id() {
        return generos_id;
    }

    public void setGeneros_id(List<Long> generos_id) {
        this.generos_id = generos_id;
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
