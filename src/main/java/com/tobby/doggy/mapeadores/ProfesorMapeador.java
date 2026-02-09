package com.tobby.doggy.mapeadores;

import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

    public Profesor actualizarProfesor (Profesor profesor){
        return new Profesor(
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getEmail(),
                profesor.getMatricula(),
                profesor.getMateria()
        );
    }

    public Page<ProfesorRespuesta> listar(String[] orden, int tamanio, int pagina) {
        return null;
    }
}
