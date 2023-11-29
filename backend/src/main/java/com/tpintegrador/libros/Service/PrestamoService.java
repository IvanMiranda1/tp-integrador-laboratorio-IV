package com.tpintegrador.libros.Service;

import com.tpintegrador.libros.Models.Libro.Libro;
import com.tpintegrador.libros.Models.Libro.LibroRepository;
import com.tpintegrador.libros.Models.Prestamo.Prestamo;
import com.tpintegrador.libros.Models.Prestamo.PrestamoDTO;
import com.tpintegrador.libros.Models.Prestamo.PrestamoRepository;
import com.tpintegrador.libros.Models.Usuario.Usuario;
import com.tpintegrador.libros.Models.Usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PrestamoService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PrestamoRepository prestamoRepository;
    public Prestamo CrearPrestamo(PrestamoDTO prestamoDTO){
        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setFecha_prestamo(prestamoDTO.getFecha_prestamo());
        Libro libro = libroRepository.findById(prestamoDTO.getLibro_id())
                .orElseThrow(()-> new EntityNotFoundException("No se encontro el libro con el ID "+prestamoDTO.getLibro_id()));
        Usuario usuario = usuarioRepository.findById(prestamoDTO.getUsuario_id())
                .orElseThrow(()-> new EntityNotFoundException("No se encontro el usuario con el ID "+prestamoDTO.getPrestamo_id()));
        nuevoPrestamo.setLibro(libro);
        nuevoPrestamo.setUsuario(usuario);
        return nuevoPrestamo;
    }

    public List<PrestamoDTO> getAllPrestamos(){
        List<Prestamo> prestamos = prestamoRepository.findAll();
        return prestamos.stream()
                .map(this::convertirPrestamoDTO)
                .collect(Collectors.toList());
    }

    public PrestamoDTO convertirPrestamoDTO(Prestamo prestamo){
        PrestamoDTO prestamoDTO = new PrestamoDTO();
        prestamoDTO.setPrestamo_id(prestamo.getPrestamo_id());
        prestamoDTO.setUsuario_id(prestamo.getUsuario().getUsuario_id());
        prestamoDTO.setLibro_id(prestamo.getLibro().getLibro_id());
        prestamoDTO.setFecha_prestamo(prestamo.getFecha_prestamo());
        prestamoDTO.setEstado(prestamo.getEstado());
        return prestamoDTO;
    }

    public PrestamoDTO getPrestamoById(Long idPrestamo){
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(()-> new EntityNotFoundException("No se encontro el prestamo con esta ID: "+idPrestamo));
        return convertirPrestamoDTO(prestamo);
    }

    public ResponseEntity<String> deletePrestamo(Long id){
        Prestamo prestamo = prestamoRepository.findById(id).orElse(null);
        if(prestamo != null){
            if (prestamo.getEstado() != null){
                if(prestamo.getEstado() == false) {
                    //Forbidden = solicitud valida y pero no se puede realizar la accion por falta de recursos...
                    return ResponseEntity
                            .status(HttpStatus.FORBIDDEN).body("El estado del prestamo aun es false (no deveuelto)");
                } else {
                    //Si es true
                    prestamoRepository.deleteById(prestamo.getPrestamo_id());
                    ResponseEntity
                            .ok("PRESTAMO ELIMINADO.   ");
                }
            }
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).body("El prestamo estado es null");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND).body("Prestamo no encontrado");
    }
    @Transactional
    public ResponseEntity<String> devuelto(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id).orElse(null);
        if(prestamo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestamo no encontrado");
        }
        prestamo.setEstado(true);
        return ResponseEntity.ok("Prestamo devuelto");
    }

    @Transactional
    public ResponseEntity<String> modificarPrestamo(PrestamoDTO prestamoDTO){
        Prestamo prestamo = prestamoRepository.findById(prestamoDTO.getPrestamo_id()).orElse(null);
        if(prestamo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestamo no encontrado");
        }
        if(prestamo.getEstado() == false) {
            if(prestamoDTO.getLibro_id() != null && prestamoDTO.getLibro_id() != 0){
                Libro libro = libroRepository.findById(prestamoDTO.getLibro_id()).orElse(null);
                if(libro != null){
                    prestamo.setLibro(libro);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
                }
            }
            if(prestamoDTO.getUsuario_id() != null && prestamoDTO.getUsuario_id() != 0){
                Usuario usuario = usuarioRepository.findById(prestamoDTO.getUsuario_id()).orElse(null);
                if (usuario != null){
                    prestamo.setUsuario(usuario);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");

                }
            }
            if(prestamoDTO.getFecha_prestamo() != null){
                prestamo.setFecha_prestamo(prestamoDTO.getFecha_prestamo());
            }
            if(prestamoDTO.getEstado() != null){
                prestamo.setEstado(prestamoDTO.getEstado());
            }
            return ResponseEntity.ok("Prestamo Modificado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro devuelto, no se permite su modificacion...");
        }
    }
}
