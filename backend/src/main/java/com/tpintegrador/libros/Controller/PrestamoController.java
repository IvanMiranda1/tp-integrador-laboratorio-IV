package com.tpintegrador.libros.Controller;

import com.tpintegrador.libros.Models.Prestamo.Prestamo;
import com.tpintegrador.libros.Models.Prestamo.PrestamoDTO;
import com.tpintegrador.libros.Models.Prestamo.PrestamoRepository;
import com.tpintegrador.libros.Service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamo")
public class PrestamoController {
    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private PrestamoService prestamoService;
    @GetMapping
    public List<Prestamo> getAllPrestamo() {
        return prestamoRepository.findAll();
    }
    @GetMapping("/conIds")
    public List<PrestamoDTO> getAllPrestamos(){
        return prestamoService.getAllPrestamos();
    }
    @GetMapping("/{id}")
    public PrestamoDTO getPrestamoById(@PathVariable Long id) {
        return prestamoService.getPrestamoById(id);
    }
    @PostMapping
    public Prestamo createPrestamo(@RequestBody PrestamoDTO prestamoDTO){
        Prestamo nuevoPrestamo = prestamoService.CrearPrestamo(prestamoDTO);
        return prestamoRepository.save(nuevoPrestamo);
    }
    @DeleteMapping("/{id}")
    public void deletePrestamo(@PathVariable Long id){
        prestamoService.deletePrestamo(id);
    }
    @PutMapping
    public void modificarPrestamo(@RequestBody PrestamoDTO prestamoDTO){
        prestamoService.modificarPrestamo(prestamoDTO);
    }
    @PutMapping("/{id}")
    public void libroDevuelto(@PathVariable Long id){
        prestamoService.devuelto(id);
    }

}
