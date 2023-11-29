package com.tpintegrador.libros.Controller;

import com.tpintegrador.libros.Models.Genero.Genero;
import com.tpintegrador.libros.Models.Genero.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genero")
public class GeneroController {
    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping
    public List<Genero> getAllGeneros(){ return generoRepository.findAll();}
    @GetMapping("/{id}")
    public Genero getGeneroById(@PathVariable Long id){ return generoRepository.findById(id).get();}
    @PostMapping
    public Genero createGenero(@RequestBody Genero genero) {return generoRepository.save(genero);}
}
