package com.tobby.doggy.modelado.interfaces;

import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.MateriaRespuesta;

import java.util.List;

public interface IMateria {

    List<MateriaRespuesta> crear (Alumno alumno);
    List<Materia> actualizar(Alumno alumno, AlumnoPeticion alumnoPeticion);
    List<MateriaRespuesta> listar();
}
