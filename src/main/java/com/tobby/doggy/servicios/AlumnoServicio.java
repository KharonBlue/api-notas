package com.tobby.doggy.servicios;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.mapeadores.AlumnoMapeador;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.interfaces.IAlumno;
import com.tobby.doggy.modelado.interfaces.IMateria;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServicio implements IAlumno {

    @Autowired
    private AlumnoMapeador alumnoMapeador;
    @Autowired
    private IAlumnoRepositorio alumnoRepositorio;
    @Autowired
    private MateriaServicio materiaServicio;

    @Override
    public AlumnoRespuesta crear(AlumnoPeticion alumnoPeticion) {
        Alumno alumno = alumnoMapeador.crear(alumnoPeticion);
        alumnoRepositorio.save(alumno);
        materiaServicio.crear(alumno);
        return alumnoMapeador.crearRespuesta(alumno);
    }

    @Override
    public AlumnoRespuesta actualizar(Long id, AlumnoPeticion alumnoPeticion) {
        Alumno alumno = alumnoMapeador.actualizar(id, alumnoPeticion);
        materiaServicio.actualizar(alumno, alumnoPeticion);
        alumnoRepositorio.save(alumno);
        return alumnoMapeador.crearRespuesta(alumno);
    }

    @Override
    public String eliminar(Long id) {
        Optional<Alumno> resultado = alumnoRepositorio.findById(id);
        if (resultado.isPresent()) {
            alumnoRepositorio.delete(resultado.get());
            return "Alumno eliminado";
        }
        throw new IdNoEncontrado("El id ingresado no existe");
    }

    @Override
    public List<AlumnoRespuesta> listar() {
        return alumnoMapeador.listar(alumnoRepositorio.findAll());
    }
}
