package com.tobby.doggy.servicios;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.mapeadores.AlumnoMapeador;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.interfaces.IAlumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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
        log.info("Alumno = {} creado", alumno.getId());
        return alumnoMapeador.crearRespuesta(alumno);
    }

    @Override
    public AlumnoRespuesta actualizar(Long id, AlumnoPeticion alumnoPeticion) {
        log.debug("Alumno a actualizar= {}, {}", id, alumnoPeticion.getNombre());
        Optional<Alumno> resultado = alumnoRepositorio.findById(id);
        if (resultado.isEmpty()) {
            throw new IdNoEncontrado("El id ingresado no existe");
        }
        Alumno alumno = alumnoMapeador.actualizar(id, alumnoPeticion, resultado.get());
        materiaServicio.actualizar(alumno, alumnoPeticion);
        alumnoRepositorio.save(alumno);
        log.info("Alumno actualizado={}", alumno.getNombre());
        return alumnoMapeador.crearRespuesta(alumno);
    }

    @Override
    public String eliminar(Long id) {
        Optional<Alumno> resultado = alumnoRepositorio.findById(id);
        if (resultado.isPresent()) {
            alumnoRepositorio.delete(resultado.get());
            log.info("Alumno eliminado {}", id);
            return "Alumno eliminado";
        }
        log.warn("Alumno con id {} no existe", id);
        throw new IdNoEncontrado("El id ingresado no existe");
    }

    @Override
    public Page<AlumnoRespuesta> listar(int pagina, int tamanio, String orden) {

        Sort ordenConfig = Sort.by(orden).ascending();
        if (orden.equalsIgnoreCase("desc")) {
            ordenConfig = ordenConfig.descending();
        }
        Pageable pageable = PageRequest.of(pagina, tamanio, ordenConfig);
        Page<Alumno> respuestaPaginable = alumnoRepositorio.findAll(pageable);

        return alumnoMapeador.listar(respuestaPaginable);
    }

}
