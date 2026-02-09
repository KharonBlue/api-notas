package com.tobby.doggy.sembradores;

import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import com.tobby.doggy.repositorios.IProfesorRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class ProfesorSembrador implements CommandLineRunner {

    @Autowired
    private final IProfesorRepositorio profesorRepositorio;

    public ProfesorSembrador(IProfesorRepositorio profesorRepositorio) {
        this.profesorRepositorio = profesorRepositorio;
    }

    @Override
    public void run(String... args) throws Exception {


        if (profesorRepositorio.count() == 0) {

            String[] nombres = {"Facundo", "Ricardo", "Laura", "Frank", "Jimi", "Chester"};
            String[] apellidos = {"Castro", "Martinez", "Colorado", "Sinatra", "Hendrix", "Benington"};
            String[] emails = {"arte@gmail.com", "edfisica@gmail.com", "lengua@gmail.com", "historia@gmail.com", "naturaleza@gmail.com", "matematicas@gmail.com"};
            Integer[] matriculas = {1001, 1201, 1301, 1401, 1501, 1601};
            NombreMateria[] nombreMaterias = {NombreMateria.ARTES, NombreMateria.ED_FISICA, NombreMateria.LENGUA, NombreMateria.HISTORIA, NombreMateria.NATURALEZA, NombreMateria.MATEMATICAS};

            for (int i = 0; i < 7; i++) {
                Profesor profesor = new Profesor(nombres[i], apellidos[i], emails[i], matriculas[i], nombreMaterias[i], LocalDate.now());
                profesorRepositorio.save(profesor);
                log.info("Profesor creado: {}", profesor.toString());
            }
            log.info("Finalización de insercion de profesores");
        }
    }
}
