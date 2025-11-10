package com.tobby.doggy.modelado.interfaces;

import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import org.springframework.data.domain.Page;

public interface IAlumno {

    AlumnoRespuesta crear(AlumnoPeticion alumnoPeticion);

    AlumnoRespuesta actualizar(Long id, AlumnoPeticion alumnoPeticion);

    String eliminar(Long id);

    public Page<AlumnoRespuesta> listar(String[] orden, int tamanio, int pagina);
}
