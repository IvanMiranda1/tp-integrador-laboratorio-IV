package com.tpintegrador.libros.Models.Prestamo;

import com.tpintegrador.libros.Models.Libro.Libro;
import com.tpintegrador.libros.Models.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    @Query(value = "SELECT p FROM Prestamo p WHERE p.libro.libro_id = :libroId")
    Prestamo findByLibro(@Param("libroId") Long libroId);

    @Query(value = "SELECT p FROM Prestamo p WHERE p.usuario.usuario_id = :usuarioId")
    Prestamo findByUsuario(@Param("usuarioId") Long usuarioId);
}
