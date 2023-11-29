package com.tpintegrador.libros.Controller;

import com.tpintegrador.libros.Models.Genero.Genero;
import com.tpintegrador.libros.Models.Libro.Libro;
import com.tpintegrador.libros.Models.Libro.LibroP;
import com.tpintegrador.libros.Models.Libro.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tpintegrador.libros.Service.LibroService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/libro")
public class LibroController {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> getAllLibros(){
        return libroRepository.findAll();
    }

    @GetMapping("/conGenero")
    public List<LibroP> obtenerLibrosConGeneros(){
        return libroService.getAllLibros();
    }

    @GetMapping("/busq/{busqueda}")
    public List<LibroP> getLibroById(@PathVariable String busqueda){
        return libroService.getLibrosByBusqueda(busqueda);
    }

    @GetMapping("/{id}")
    public LibroP getLibroById(@PathVariable Long id) {
        return libroService.GetLibroById(id);
    }

    @PostMapping
    public Libro createLibro(@RequestBody LibroP libro) {
        Libro nuevoLibro = libroService.CrearLibro(libro);
        return libroRepository.save(nuevoLibro);
    }
    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id){
        libroService.comprobacionDePrestamo(id);
    }

    @PutMapping
    public void modificarLibro(@RequestBody LibroP libroDTO){
        libroService.modificarLibro(libroDTO);
    }
}
