package com.tpintegrador.libros.Service;

import com.tpintegrador.libros.Models.Prestamo.Prestamo;
import com.tpintegrador.libros.Models.Prestamo.PrestamoRepository;
import com.tpintegrador.libros.Models.Usuario.Usuario;
import com.tpintegrador.libros.Models.Usuario.UsuarioDTO;
import com.tpintegrador.libros.Models.Usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PrestamoRepository prestamoRepository;
    public Usuario CrearUsuario(UsuarioDTO usuarioDTO){
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNomyape(usuarioDTO.getNomyape());
        nuevoUsuario.setUsuario(usuarioDTO.getUsuario());
        return nuevoUsuario;
    }

    public List<UsuarioDTO> getAllUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertirUsuarioDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO convertirUsuarioDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuario_id(usuario.getUsuario_id());
        usuarioDTO.setUsuario(usuario.getUsuario());
        usuarioDTO.setNomyape(usuario.getNomyape());
        usuarioDTO.setPrestamo_id(usuario.getPrestamo().getPrestamo_id());
        return usuarioDTO;
    }

    public ResponseEntity<String> comprobacionDePrestamo(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        Prestamo prestamo = prestamoRepository.findByUsuario(usuario.getUsuario_id());
        if (prestamo != null) {
            if(prestamo.getEstado() == false){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con prestamos activos");
            }
            usuarioRepository.delete(usuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario eliminado");
        } else {
            usuarioRepository.delete(usuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario eliminado");
        }
    }

    @Transactional
    public ResponseEntity<String> modificarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getUsuario_id()).orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        if(usuarioEnPrestamoActivo(usuario.getUsuario_id())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario en un prestamo activo");
        } else {
            if (usuarioDTO.getNomyape() != null) {
                usuario.setNomyape(usuarioDTO.getNomyape());
            }
            if (usuarioDTO.getUsuario() != null) {
                usuario.setUsuario(usuarioDTO.getUsuario());
            }
            return ResponseEntity.ok("Usuario Modificado");
        }
    }

    public boolean usuarioEnPrestamoActivo(Long idBusq){
        List<Prestamo> prestamos = prestamoRepository.findAll();
        for(Prestamo prestamo : prestamos){
            if(prestamo != null){
                if(idBusq.equals(prestamo.getUsuario().getUsuario_id())){
                    if(prestamo.getEstado() == false || prestamo.getEstado() == null){
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                prestamo.setEstado(false);
            }
        }
        return false;
    }
}
