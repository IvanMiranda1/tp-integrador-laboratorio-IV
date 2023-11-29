package com.tpintegrador.libros.Service;

import com.tpintegrador.libros.Models.Genero.Genero;
import com.tpintegrador.libros.Models.Genero.GeneroRepository;
import com.tpintegrador.libros.Models.Libro.Libro;
import com.tpintegrador.libros.Models.Libro.LibroP;
import com.tpintegrador.libros.Models.Libro.LibroRepository;
import com.tpintegrador.libros.Models.Prestamo.Prestamo;
import com.tpintegrador.libros.Models.Prestamo.PrestamoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<LibroP> getLibrosByBusqueda(String busqueda){
        List<Libro> libros = libroRepository.findAll();
        List<LibroP> librosp = new ArrayList<>();
        for (Libro libro : libros){
            if(libro.getTitulo().compareToIgnoreCase(busqueda)==0){
                librosp.add(convertirLibroP(libro));
            }
        }
        return librosp;
    }

    public List<LibroP> getAllLibros(){
        List<Libro> libros = libroRepository.findAll();
        return libros.stream()
                .map(this::convertirLibroP)
                .collect(Collectors.toList());
        //collect metodo de Stream que realiza una operacion acumulando los elementos
        //Collectors es una utilidad de java
        // y collectors.tolist es un metodo que acumula los elementos de una lista
    }

    public Libro CrearLibro(LibroP libroP){
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(libroP.getTitulo());
        nuevoLibro.setAutor(libroP.getAutor());
        nuevoLibro.setSinopsis(libroP.getSinopsis());
        nuevoLibro.setCantpag(libroP.getCantpag());
        List<Genero> generos = new ArrayList<>();
        for(Long idGenero : libroP.getGeneros_id()) {
            Genero genero = generoRepository.findById(idGenero)
                    .orElseThrow(()-> new EntityNotFoundException("No se encontro el genero con el ID " + idGenero));
            generos.add(genero);

        }
        nuevoLibro.setGeneros(generos);
        return nuevoLibro;
    }

    public LibroP convertirLibroP(Libro libro){
        LibroP libroP = new LibroP();
        libroP.setLibro_id(libro.getLibro_id());
        libroP.setTitulo(libro.getTitulo());
        libroP.setAutor(libro.getAutor());
        libroP.setSinopsis(libro.getSinopsis());
        libroP.setCantpag(libro.getCantpag());
        libroP.setGeneros_id(libro.getGeneros().stream().map(Genero::getGenero_id).collect(Collectors.toList()));
        //Aca se aplica el mismo metodo que en getAllLibros
        //se recorre el array de generos y una vez obtenidos los generos(elementos) se guardan en collectors.tolist()
        return libroP;
    }

    public LibroP GetLibroById(Long idLibro){
        Libro libro = libroRepository.findById(idLibro)
                .orElseThrow(()-> new EntityNotFoundException("No se encontro el libro con este ID: "+idLibro));
        return convertirLibroP(libro);
    }

    public ResponseEntity<String> comprobacionDePrestamo(Long id){
        //La funcion con response entity me permite lanzar mensajes de errores y frenar la ejecucion de la funcion....
        Libro libro = libroRepository.findById(id).orElse(null);
        if(libro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
        }
        Prestamo prestamo = prestamoRepository.findByLibro(libro.getLibro_id());
        if (prestamo != null ){
            if(prestamo.getEstado() == false){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro con prestamos activos");
            } else {
                //no hay prestamos activos
                eliminarLibro(id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro eliminado");
            }
        } else {
            eliminarLibro(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro eliminado");
        }
    }

    public void eliminarLibro(Long id){
        Libro libro = libroRepository.findById(id).orElse(null);
        if(libro != null){
            List<Genero> generos = new ArrayList<>(libro.getGeneros());
            for(Genero genero : generos){
                libro.getGeneros().remove(genero);
            }
            libroRepository.delete(libro);
        }
    }
    @Transactional
    public ResponseEntity<String> modificarLibro(LibroP libroDTO){
        Libro libro = libroRepository.findById(libroDTO.getLibro_id()).orElse(null);

        if(libro == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
        }

        if(libroEnPrestamoActivo(libro.getLibro_id())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro en un prestamo activo");
        } else {
            if (libroDTO.getTitulo() != null) {
                libro.setTitulo(libroDTO.getTitulo());
            }

            if (libroDTO.getAutor() != null) {
                libro.setAutor(libroDTO.getAutor());
            }

            if (libroDTO.getGeneros_id() != null && !libroDTO.getGeneros_id().isEmpty()) {
                List<Genero> generos = new ArrayList<>();
                for(Long idGenero : libroDTO.getGeneros_id()) {
                    Genero genero = generoRepository.findById(idGenero)
                            .orElseThrow(()-> new EntityNotFoundException("No se encontro el genero con el ID " + idGenero));
                    generos.add(genero);
                }
                libro.setGeneros(generos);
            }

            if (libroDTO.getCantpag() > 0) {
                libro.setCantpag(libroDTO.getCantpag());
            }

            if (libroDTO.getSinopsis() != null) {
                libro.setSinopsis(libroDTO.getSinopsis());
            }
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).body("Libro Modificado");
        }
    }

    public boolean libroEnPrestamoActivo(Long idBusq){
        List<Prestamo> prestamos = prestamoRepository.findAll();
        for (Prestamo prestamo : prestamos){
            if(prestamo != null){
                if (idBusq.equals(prestamo.getLibro().getLibro_id())){
                    if(prestamo.getEstado() == false){
                        return true;//el estado en false quiere decir que no fue devuelto, quiere decir que el prestamo esta "activo"
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

}
