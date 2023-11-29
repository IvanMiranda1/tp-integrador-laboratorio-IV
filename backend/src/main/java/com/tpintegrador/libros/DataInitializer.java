package com.tpintegrador.libros;

import com.tpintegrador.libros.Models.Genero.Genero;
import com.tpintegrador.libros.Models.Genero.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private GeneroRepository generoRepository;
    @Override
    public void run(String... args) throws Exception {

        Genero generoRealismo = new Genero("Realismo");
        generoRepository.save(generoRealismo);

        Genero generoTerrorSuspenso = new Genero("Terror/Suspenso");
        generoRepository.save(generoTerrorSuspenso);

        Genero generoDrama = new Genero("Drama");
        generoRepository.save(generoDrama);

        Genero generoFC = new Genero("Ciencia Ficcion");
        generoRepository.save(generoFC);

        Genero generoAventura = new Genero("Aventura");
        generoRepository.save(generoAventura);

        Genero generoFantasia = new Genero("Fantasia");
        generoRepository.save(generoFantasia);

        Genero generoAmor = new Genero("Amor");
        generoRepository.save(generoAmor);

        Genero generoComedia = new Genero("Comedia");
        generoRepository.save(generoComedia);

        Genero generoHistorico = new Genero("Histórico");
        generoRepository.save(generoHistorico);

        Genero generoAcccion = new Genero("Acción");
        generoRepository.save(generoAcccion);
        
        Genero generoMisterioThriller = new Genero("Misterio/Thriller");
        generoRepository.save(generoMisterioThriller);
    }
}
