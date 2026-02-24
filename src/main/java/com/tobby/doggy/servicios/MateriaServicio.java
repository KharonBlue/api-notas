package com.tobby.doggy.servicios;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.mapeadores.MateriaMapeador;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import com.tobby.doggy.modelado.interfaces.IMateria;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.MateriaRespuesta;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaServicio implements IMateria {

    @Autowired
    private MateriaMapeador materiaMapeador;
    @Autowired
    private IMateriaRepositorio materiaRepositorio;
    @Autowired
    private ProfesorServicio profesorServicio;

    @Override
    public List<MateriaRespuesta> crear(Alumno alumno) {
        materiaRepositorio.saveAll(alumno.getMaterias());
        return materiaMapeador.mapear(alumno.getMaterias());
    }

    //Aun por hacer
    @Override
    public List<Materia> actualizar(Alumno alumno, AlumnoPeticion alumnoPeticion) {
        return materiaRepositorio.saveAll(materiaMapeador.actualizar(alumno, alumnoPeticion));
    }

    @Override
    public List<MateriaRespuesta> listar() {
        return materiaMapeador.listar();
    }
/*
    public Materia asignarProfesor(NombreMateria nombreMateria, Profesor profesor) {
        Optional<Materia> resultado = materiaRepositorio.findByNombreMateria(nombreMateria.getNombreMateria());

        if (resultado.isEmpty()) {
            throw new IdNoEncontrado("La materia ingresada no existe");
        }

        if (profesorServicio.comprobarProfesor(profesor.getId()) != null) {
            if (profesor.getMateria().equals(resultado.get().getNombreMateria())) {
                Materia materia = resultado.get();
                materia.setProfesorAsignado(profesor);
                materiaRepositorio.save(materia);
                return materia;
            }
        }
        return null;
    }*/

}
