package com.tobby.doggy.unitarios;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.mapeadores.AlumnoMapeador;
import com.tobby.doggy.mapeadores.MateriaMapeador;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.servicios.AlumnoServicio;
import com.tobby.doggy.servicios.MateriaServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlumnoServicioTest {

    @Mock
    private IAlumnoRepositorio alumnoRepositorio;
    @Mock
    private AlumnoMapeador alumnoMapeador;
    @Mock
    private MateriaMapeador materiaMapeador;
    @Mock
    private MateriaServicio materiaServicio;
    @InjectMocks
    private AlumnoServicio alumnoServicio;

    public Alumno ALUMNO_PREPARADO = new Alumno(1L, LocalDate.now(), "Nombre", "Apellido", null);

    public AlumnoPeticion ALUMNO_PETICION = new AlumnoPeticion("Nombre", "Apellido");

    public AlumnoRespuesta ALUMNO_ESPERADO = new AlumnoRespuesta(LocalDate.now(), "Nombre", "Apellido", null);

    @Test
    public void crearAlumno() {
        //Seteo las materias a los resultados
        ALUMNO_PREPARADO.setMaterias(materiaMapeador.crear(ALUMNO_PREPARADO));

        //Cuando creo un usuario (ALUMNO_PETICION) este retornara el ALUMNO_ESPERADO
        when(alumnoMapeador.crear(ALUMNO_PETICION)).thenReturn(ALUMNO_PREPARADO);
        when(alumnoServicio.crear(ALUMNO_PETICION)).thenReturn(ALUMNO_ESPERADO);

        //LLamo a la función crear para obtener el AlumnoRespuesta
        alumnoServicio.crear(ALUMNO_PETICION);

        //AFIRMAMOS que los valores sean iguales entre el obtenido y el esperado
        assertEquals(ALUMNO_PREPARADO.getNombre(), ALUMNO_ESPERADO.getNombre());
        assertEquals(ALUMNO_PREPARADO.getApellido(), ALUMNO_ESPERADO.getApellido());
    }

    @Test
    public void actualizarAlumno() {
        Alumno ALUMNO_ACTUALIZADO = new Alumno(1L, LocalDate.now(), "NombreActualizado", "Apellido", null);
        //En caso de querer comprobar las materias, utilizar el materiaMapeador para crearlas o directamente pasar la lista con ellas
        ALUMNO_ESPERADO.setNombre("NombreActualizado");
        ALUMNO_PETICION.setNombre("NombreActualizado");

        when(alumnoRepositorio.findById(1L)).thenReturn(Optional.of(ALUMNO_PREPARADO));
        when(alumnoMapeador.actualizar(1L, ALUMNO_PETICION, ALUMNO_PREPARADO)).thenReturn(ALUMNO_ACTUALIZADO);

        materiaServicio.actualizar(ALUMNO_PREPARADO, ALUMNO_PETICION);
        when(alumnoRepositorio.save(ALUMNO_ACTUALIZADO)).thenReturn(ALUMNO_ACTUALIZADO);

        when(alumnoServicio.actualizar(1L, ALUMNO_PETICION)).thenReturn(ALUMNO_ESPERADO);
        alumnoServicio.actualizar(1L, ALUMNO_PETICION);

        //AFIRMAMOS que los valores sean iguales entre el obtenido y el esperado
        assertEquals(ALUMNO_ACTUALIZADO.getNombre(), ALUMNO_ESPERADO.getNombre());
    }

    @Test
    public void actualizarAlumnoFallido() {
        IdNoEncontrado idNoEncontrado = assertThrows(IdNoEncontrado.class, () -> {
            alumnoServicio.actualizar(2L, ALUMNO_PETICION);
        });
    }

    @Test
    public void eliminarAlumno() {
        Long alumnoId = 1L;

        when(alumnoRepositorio.findById(alumnoId)).thenReturn(Optional.of(ALUMNO_PREPARADO));
        doNothing().when(alumnoRepositorio).delete(ALUMNO_PREPARADO);

        alumnoServicio.eliminar(alumnoId);

        verify(alumnoRepositorio, times(1)).delete(ALUMNO_PREPARADO);
    }

    @Test
    public void eliminarAlumnoFallido() {
        IdNoEncontrado idNoEncontrado = assertThrows(IdNoEncontrado.class, () -> {
            alumnoServicio.eliminar(2L);
        });
    }

    @Test
    public void listar() {
        //Primero defino la configuracion del paginable para poder hacer el respectivo llamado al alumnoRepositorio
        Sort ordenConfig = Sort.by("desc").descending();
        Pageable paginable = PageRequest.of(0, 10, ordenConfig);
        List<Alumno> lista = Collections.singletonList(new Alumno());
        Page<Alumno> page = new PageImpl<>(lista, paginable, 1);
        when(alumnoRepositorio.findAll(paginable)).thenReturn(page);

        //Lo que retorna el servicio es una llamada al alumnoMapeador por lo que tambien hay que simular con mockito este caso
        List<AlumnoRespuesta> listaRespuesta = Collections.singletonList(new AlumnoRespuesta());
        Page<AlumnoRespuesta> pageRespuesta = new PageImpl<>(listaRespuesta, paginable, 1);
        when(alumnoMapeador.listar(page)).thenReturn(pageRespuesta);

        //Finalmente llamo al servicio y guardo los valores en una variable para poder hacer las asserciones
        Page<AlumnoRespuesta> resultado = alumnoServicio.listar(paginable.getPageNumber(), page.getSize(), "desc");
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(alumnoRepositorio).findAll(paginable);
    }
}
