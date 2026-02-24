package com.tobby.doggy.mapeadores;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AlumnoMapeador {

    @Autowired
    private IAlumnoRepositorio alumnoRepositorio;
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

    public Alumno actualizar(Long id, AlumnoPeticion alumnoPeticion) {
        Optional<Alumno> resultado = alumnoRepositorio.findById(id);
        if (resultado.isPresent()) {
            Alumno alumno = resultado.get();
            alumno.setNombre(alumnoPeticion.getNombre());
            alumno.setApellido(alumnoPeticion.getApellido());
            alumno.setMaterias(materiaMapeador.actualizar(alumno, alumnoPeticion));
            return alumno;
        }
        throw new IdNoEncontrado("El id ingresado no existe");
    }

    public Page<AlumnoRespuesta> listar(String[] orden, int tamanio, int pagina) {

        Sort ordenConfig = Sort.by(orden[0]).ascending();
        if (orden.length > 1 && orden[1].equalsIgnoreCase("desc")) {
            ordenConfig = ordenConfig.descending();
        }
        Pageable pageable = PageRequest.of(pagina, tamanio, ordenConfig);
        Page<Alumno> respuestaPageable = alumnoRepositorio.findAll(pageable);

        return respuestaPageable.map(this::crearRespuesta);
    }

    public AlumnoRespuesta crearRespuesta(Alumno alumno) {
        return new AlumnoRespuesta(alumno.getCreacion(), alumno.getNombre(), alumno.getApellido(), materiaMapeador.mapear(alumno.getMaterias()));
    }

}
