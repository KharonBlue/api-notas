package com.tobby.doggy.sembradores;

import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import com.tobby.doggy.repositorios.IProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfesorSembrador implements ApplicationRunner {

    private final IProfesorRepositorio profesorRepositorio;
    private final IMateriaRepositorio materiaRepositorio;
    private final IAlumnoRepositorio alumnoRepositorio;

    public ProfesorSembrador(IProfesorRepositorio profesorRepositorio, IMateriaRepositorio materiaRepositorio, IAlumnoRepositorio alumnoRepositorio){
        this.profesorRepositorio = profesorRepositorio;
        this.materiaRepositorio = materiaRepositorio;
        this.alumnoRepositorio = alumnoRepositorio;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(profesorRepositorio.count() == 0){
            profesorRepositorio.save(new Profesor());
        }
    }

    private Profesor crear(String nombre, String apellido, String email, List<Alumno> alumnos, Integer matricula){

        List<Materia> materias = materiaRepositorio.findAll();
        return new Profesor(nombre, apellido, email,123,materias, alumnos);
    }
}
