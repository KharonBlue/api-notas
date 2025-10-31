package com.tobby.doggy.mapeadores;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.peticiones.MateriaPeticion;
import com.tobby.doggy.modelado.respuestas.MateriaRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MateriaMapeador {

    @Autowired
    IAlumnoRepositorio alumnoRepositorio;
    @Autowired
    IMateriaRepositorio materiaRepositorio;

    public List<MateriaRespuesta> listar() {
        List<MateriaRespuesta> materias = new ArrayList<>();
        for (Materia m : materiaRepositorio.findAll()) {
            materias.add(crearRespuesta(m));
        }
        return materias;
    }


    public List<Materia> mapear(List<MateriaPeticion> materiaPeticiones) {
        List<Materia> materias = materiaRepositorio.findAll();
        for (MateriaPeticion m : materiaPeticiones) {
            Materia resultado = materiaRepositorio.findByNombreMateria(m.getNombre());
            resultado.setAprobada(m.isAprobada());
            resultado.setPuntaje(m.getPuntaje());
            materias.set(resultado.getId().byteValue(), resultado);
        }
        return materias;
    }

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
    }

    public MateriaRespuesta crearRespuesta(Materia materia) {
        return new MateriaRespuesta(materia.getNombreMateria(), materia.getPuntaje(), materia.isAprobada());
    }
}
