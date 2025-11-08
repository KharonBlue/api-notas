/*package com.tobby.doggy.sembradores;

import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Profile("dev") usar en caso de desarrollo
@Component
public class MateriaSembrador implements CommandLineRunner {

    @Autowired
    private final IMateriaRepositorio materiaRepositorio;

    public MateriaSembrador(IMateriaRepositorio materiaRepositorio) {
        this.materiaRepositorio = materiaRepositorio;
    }

    @Override
    public void run(String... args) throws Exception {
        if (materiaRepositorio.count() == 0) {
            materiaRepositorio.save(new Materia(LocalDate.now(), NombreMateria.ARTES, 0, false));
            materiaRepositorio.save(new Materia(LocalDate.now(), NombreMateria.ED_FISICA, 0, false));
            materiaRepositorio.save(new Materia(LocalDate.now(), NombreMateria.LENGUA, 0, false));
            materiaRepositorio.save(new Materia(LocalDate.now(), NombreMateria.HISTORIA, 0, false));
            materiaRepositorio.save(new Materia(LocalDate.now(), NombreMateria.MATEMATICAS, 0, false));
            materiaRepositorio.save(new Materia(LocalDate.now(), NombreMateria.NATURALEZA, 0, false));
            System.out.println("Materias agregadas a la base de datos");
        }
    }
}*/
