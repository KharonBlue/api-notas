package com.tobby.doggy.modelado.interfaces;

import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;

import java.util.List;

public interface IAlumno {

    AlumnoRespuesta crear(AlumnoPeticion alumnoPeticion);

    AlumnoRespuesta actualizar(Long id, AlumnoPeticion alumnoPeticion);

    String eliminar(Long id);

    List<AlumnoRespuesta> listar();
}
