package com.tobby.doggy.sembradores;

import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Profile("dev") usar en caso de desarrollo
@Component
public class MateriaSembrador implements ApplicationRunner {

    private final IMateriaRepositorio materiaRepositorio;

    public MateriaSembrador(IMateriaRepositorio materiaRepositorio) {
        this.materiaRepositorio = materiaRepositorio;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
}
