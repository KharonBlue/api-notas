package com.tobby.doggy.modelado.interfaces;

import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesor {

    ProfesorRespuesta crearProfesor(ProfesorPeticion profesorPeticion);
    ProfesorRespuesta actualizarProfesor(Long id, ProfesorPeticion profesorPeticion);
    Page<ProfesorRespuesta> listar(String[] orden, int tamanio, int pagina);
}
