package com.tobby.doggy.mapeadores;

import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AlumnoMapeador {

    @Autowired
    private MateriaMapeador materiaMapeador;

    public Alumno crear(AlumnoPeticion alumnoPeticion) {
        Alumno alumno = new Alumno();
        alumno.setCreacion(LocalDate.now());
        alumno.setNombre(alumnoPeticion.getNombre());
        alumno.setApellido(alumnoPeticion.getApellido());
        alumno.setMaterias(materiaMapeador.crear(alumno));
        return alumno;
    }

    public Alumno actualizar(Long id, AlumnoPeticion alumnoPeticion, Alumno alumno) {
        alumno.setNombre(alumnoPeticion.getNombre());
        alumno.setApellido(alumnoPeticion.getApellido());
        alumno.setMaterias(materiaMapeador.actualizar(alumno, alumnoPeticion));
        return alumno;

    }

    public Page<AlumnoRespuesta> listar(Page<Alumno> paginable) {
        return paginable.map(this::crearRespuesta);
    }

    public AlumnoRespuesta crearRespuesta(Alumno alumno) {
        return new AlumnoRespuesta(alumno.getCreacion(), alumno.getNombre(), alumno.getApellido(), materiaMapeador.mapear(alumno.getMaterias()));
    }

}
