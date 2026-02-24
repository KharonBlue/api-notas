package com.tobby.doggy.servicios;


import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.excepciones.ResultadoNoEncontrado;
import com.tobby.doggy.mapeadores.ProfesorMapeador;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import com.tobby.doggy.modelado.interfaces.IProfesor;
import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.repositorios.IProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfesorServicio implements IProfesor {

    @Autowired
    private IProfesorRepositorio profesorRepositorio;
    @Autowired
    private IAlumnoRepositorio alumnoRepositorio;
    @Autowired
    private ProfesorMapeador mapeador;

    @Override
    public ProfesorRespuesta crearProfesor(ProfesorPeticion profesorPeticion) {
        Profesor profesor = mapeador.crearProfesor(profesorPeticion);
        profesorRepositorio.save(profesor);
        return mapeador.crearProfesorRespuesta(profesor);
    }

    @Override
    public ProfesorRespuesta actualizarProfesor(Long id, ProfesorPeticion profesorPeticion) {
        Optional<Profesor> resultado = profesorRepositorio.findById(id);
        if (resultado.isEmpty()) {
            throw new IdNoEncontrado("El id ingresado (" + id.toString() + ") no existe");
        }
        Profesor profesor = mapeador.actualizarProfesor(resultado.get(), profesorPeticion);
        profesorRepositorio.save(profesor);
        return mapeador.crearProfesorRespuesta(profesor);
    }

    public Set<Profesor> asignarProfesores(NombreMateria nombreMateria) {
        Set<Profesor> profesores = profesorRepositorio.findByMateria(nombreMateria);
        if (profesores.isEmpty()) {
            throw new ResultadoNoEncontrado("No hay profesores disponibles para la materia: " + nombreMateria.getNombreMateria());
        }
        return profesores;
    }

    @Override
    public List<ProfesorRespuesta> listar() {
        return mapeador.listar(profesorRepositorio.findAll());
    }

}
