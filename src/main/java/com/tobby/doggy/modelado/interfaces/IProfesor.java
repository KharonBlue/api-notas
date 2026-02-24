package com.tobby.doggy.modelado.interfaces;

import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfesor {

    ProfesorRespuesta crearProfesor(ProfesorPeticion profesorPeticion);
    ProfesorRespuesta actualizarProfesor(Long id, ProfesorPeticion profesorPeticion);
    List<ProfesorRespuesta> listar();
}
