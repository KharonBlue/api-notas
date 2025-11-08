package com.tobby.doggy.mapeadores;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.peticiones.MateriaPeticion;
import com.tobby.doggy.modelado.respuestas.MateriaRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MateriaMapeador {

    @Autowired
    IAlumnoRepositorio alumnoRepositorio;
    @Autowired
    IMateriaRepositorio materiaRepositorio;

    public List<Materia> crear(Alumno alumno) {
        List<Materia> materias = new ArrayList<>();
        for (NombreMateria nombreMateria : NombreMateria.values()) {
            materias.add(crear(alumno, nombreMateria));
        }
        return materias;
    }

    private Materia crear(Alumno alumno, NombreMateria nombreMateria) {
        return new Materia(LocalDate.now(), nombreMateria, 0, false, alumno);
    }


    public List<MateriaRespuesta> listar() {
        List<MateriaRespuesta> materias = new ArrayList<>();
        for (Materia m : materiaRepositorio.findAll()) {
            materias.add(crearRespuesta(m));
        }
        return materias;
    }

    public List<Materia> actualizar(Alumno alumno, AlumnoPeticion alumnoPeticion) {
        return mapear(alumnoPeticion.getMaterias(), alumno);
    }

    private List<Materia> mapear(List<MateriaPeticion> materiaPeticions, Alumno alumno) {
        List<Materia> materias = new ArrayList<>();
        for (int i = 0; i < alumno.getMaterias().size(); i++) {
            Optional<Materia> resultado = materiaRepositorio.findById(alumno.getMaterias().get(i).getId());
            if (resultado.isPresent()) {
                Materia materia = resultado.get();
                materia.setPuntaje(materiaPeticions.get(i).getPuntaje());
                materia.setAprobada(materiaPeticions.get(i).isAprobada());
                materias.add(materia);
            }
        }
        return materias;
    }

    public List<MateriaRespuesta> mapear(List<Materia> materias) {
        List<MateriaRespuesta> materiaRespuestas = new ArrayList<>();
        for (Materia m : materias) {
            materiaRespuestas.add(new MateriaRespuesta(m.getNombreMateria(), m.getPuntaje(), m.isAprobada()));
        }
        return materiaRespuestas;
    }

/*
    public List<MateriaRespuesta> listarPorId(Alumno alumno) {
        Optional<Alumno> resultado = alumnoRepositorio.findById(alumno.getId());
        if (resultado.isPresent()) {
            List<MateriaRespuesta> materias = new ArrayList<>();
            for (Materia m : resultado.get().getMaterias()) {
                materias.add(crearRespuesta(m));
            }
            return materias;
        }
        throw new IdNoEncontrado("El Alumno ingresado no existe");
    }*/

    public MateriaRespuesta crearRespuesta(Materia materia) {
        return new MateriaRespuesta(materia.getNombreMateria(), materia.getPuntaje(), materia.isAprobada());
    }
}
