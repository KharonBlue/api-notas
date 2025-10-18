package com.tobby.doggy.mapeadores;

import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;

import java.util.ArrayList;
import java.util.List;

public class AlumnoMapeador {

    public Alumno crear(AlumnoPeticion alumnoPeticion, List<Materia> materias) {
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoPeticion.getNombre());
        alumno.setApellido(alumnoPeticion.getApellido());
        alumno.setMaterias(materias);
        return alumno;
    }


    //A la hora de hacer el llamado a la base de datos debo corroborar
    //que el alumno exista
    public Alumno actualizar(int id, AlumnoPeticion alumnoPeticion) {
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoPeticion.getNombre());
        alumno.setApellido(alumnoPeticion.getApellido());
        return alumno;
    }

    public List<AlumnoRespuesta> listar(List<Alumno> alumnos) {
        List<AlumnoRespuesta> alumnoRespuestas = new ArrayList<>();
        for (Alumno alumno : alumnos) {
            alumnoRespuestas.add(crearRespuesta(alumno));
        }
        return alumnoRespuestas;
    }

    public AlumnoRespuesta crearRespuesta(Alumno alumno) {
        return new AlumnoRespuesta(alumno.getCreacion(), alumno.getNombre(), alumno.getApellido(), alumno.getMaterias());
    }

}
