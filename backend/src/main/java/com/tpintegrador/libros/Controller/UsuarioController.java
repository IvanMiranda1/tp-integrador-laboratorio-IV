package com.tpintegrador.libros.Controller;

import com.tpintegrador.libros.Models.Usuario.Usuario;
import com.tpintegrador.libros.Models.Usuario.UsuarioDTO;
import com.tpintegrador.libros.Models.Usuario.UsuarioRepository;
import com.tpintegrador.libros.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody UsuarioDTO usuario) {
        Usuario nuevoUsuario = usuarioService.CrearUsuario(usuario);
        return usuarioRepository.save(nuevoUsuario);
    }
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.comprobacionDePrestamo(id);
    }
    @PutMapping
    public void modificarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        usuarioService.modificarUsuario(usuarioDTO);
    }
}
