package com.tobby.doggy.mapeadores;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.peticiones.MateriaPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlumnoMapeador {

    @Autowired
    private IMateriaRepositorio materiaRepositorio;
    @Autowired
    private IAlumnoRepositorio alumnoRepositorio;
    private final MateriaMapeador materiaMapeador = new MateriaMapeador();

    public Alumno crear(AlumnoPeticion alumnoPeticion) {
        Alumno alumno = new Alumno();
        alumno.setCreacion(LocalDate.now());
        alumno.setNombre(alumnoPeticion.getNombre());
        alumno.setApellido(alumnoPeticion.getApellido());
        alumno.setMaterias(materiaRepositorio.findAll());
        return alumno;
    }

    public Alumno actualizarMateria(Long id, List<MateriaPeticion> materias) {
        Optional<Alumno> resultado = alumnoRepositorio.findById(id);
        if (resultado.isPresent()) {
            Alumno alumno = resultado.get();
            alumno.setMaterias(materiaMapeador.mapear(materias));
            return alumno;
        }
        throw new IdNoEncontrado("El id ingresado no existe");
    }

    public List<AlumnoRespuesta> listar(List<Alumno> alumnos) {
        List<AlumnoRespuesta> alumnoRespuestas = new ArrayList<>();
        for (Alumno alumno : alumnos) {
            alumnoRespuestas.add(crearRespuesta(alumno));
        }
        return alumnoRespuestas;
    }

    public AlumnoRespuesta crearRespuesta(Alumno alumno) {
        return new AlumnoRespuesta(alumno.getCreacion(), alumno.getNombre(), alumno.getApellido(), materiaMapeador.listarPorId(alumno));
    }

}
