package com.tobby.doggy.mapeadores;

import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProfesorMapeador {


    public Profesor crearProfesor(ProfesorPeticion profesorPeticion) {
        return new Profesor(
                profesorPeticion.getNombre(),
                profesorPeticion.getApellido(),
                profesorPeticion.getEmail(),
                profesorPeticion.getMatricula(),
                profesorPeticion.getMateria(),
                LocalDate.now()
        );
    }

    public ProfesorRespuesta crearProfesorRespuesta(Profesor profesor) {
        return new ProfesorRespuesta(
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getEmail(),
                profesor.getMatricula(),
                profesor.getMateria(),
                profesor.getCreacion()
        );
    }

    public Profesor actualizarProfesor(Profesor profesor, ProfesorPeticion peticion) {
        profesor.setNombre(peticion.getNombre());
        profesor.setApellido(peticion.getApellido());
        profesor.setEmail(peticion.getEmail());
        profesor.setMatricula(peticion.getMatricula());
        profesor.setMateria(peticion.getMateria());
        return profesor;
    }

    public List<ProfesorRespuesta> listar(List<Profesor> profesores) {
        return profesores.stream().map(this::crearProfesorRespuesta).toList();
    }
}
