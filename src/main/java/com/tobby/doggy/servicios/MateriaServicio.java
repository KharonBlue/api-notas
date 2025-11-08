package com.tobby.doggy.servicios;

import com.tobby.doggy.mapeadores.MateriaMapeador;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.interfaces.IMateria;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.MateriaRespuesta;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServicio implements IMateria {

    @Autowired
    private MateriaMapeador materiaMapeador;
    @Autowired
    private IMateriaRepositorio materiaRepositorio;

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
}
