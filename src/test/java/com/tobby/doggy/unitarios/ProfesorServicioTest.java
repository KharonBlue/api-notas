package com.tobby.doggy.unitarios;

import com.tobby.doggy.excepciones.IdNoEncontrado;
import com.tobby.doggy.mapeadores.ProfesorMapeador;
import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import com.tobby.doggy.repositorios.IProfesorRepositorio;
import com.tobby.doggy.servicios.ProfesorServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfesorServicioTest {

    @Mock
    private ProfesorMapeador profesorMapeador;
    @Mock
    private IProfesorRepositorio profesorRepositorio;
    @InjectMocks
    private ProfesorServicio profesorServicio;

    private final Profesor PROFESOR_PREPARADO = new Profesor(1L, LocalDate.now(), "Nombre", "Apellido", "email123@gmail.com", 12345, NombreMateria.ARTES);
    private final ProfesorPeticion PROFESOR_PETICION = new ProfesorPeticion("Nombre", "Apellido", "email123@gmail.com", 12345, NombreMateria.ARTES);
    private final ProfesorRespuesta PROFESOR_RESPUESTA = new ProfesorRespuesta("Nombre", "Apellido", "email123@gmail.com", 12345, NombreMateria.ARTES, LocalDate.now());

    @Test
    public void crear() {
        when(profesorMapeador.crearProfesor(PROFESOR_PETICION)).thenReturn(PROFESOR_PREPARADO);
        when(profesorRepositorio.save(PROFESOR_PREPARADO)).thenReturn(PROFESOR_PREPARADO);
        when(profesorServicio.crearProfesor(PROFESOR_PETICION)).thenReturn(PROFESOR_RESPUESTA);

        ProfesorRespuesta resultado = profesorServicio.crearProfesor(PROFESOR_PETICION);
        assertEquals(resultado.getNombre(), PROFESOR_PETICION.getNombre());
    }

    @Test
    public void actualizar() {
        ProfesorPeticion profesorPeticionActualizado = new ProfesorPeticion("NombreAct", "ApellidoAct", "email123@gmail.com", 12345, NombreMateria.ARTES);
        PROFESOR_RESPUESTA.setNombre("NombreAct");

        when(profesorRepositorio.findById(1L)).thenReturn(Optional.of(PROFESOR_PREPARADO));
        when(profesorMapeador.actualizarProfesor(PROFESOR_PREPARADO, profesorPeticionActualizado)).thenReturn(PROFESOR_PREPARADO);
        when(profesorRepositorio.save(PROFESOR_PREPARADO)).thenReturn(PROFESOR_PREPARADO);
        when(profesorServicio.actualizarProfesor(1L, profesorPeticionActualizado)).thenReturn(PROFESOR_RESPUESTA);

        ProfesorRespuesta resultado = profesorServicio.actualizarProfesor(1L, profesorPeticionActualizado);
        assertEquals("NombreAct", resultado.getNombre());
    }

    @Test
    public void actualizarFallido() {
        IdNoEncontrado idNoEncontrado = assertThrows(IdNoEncontrado.class, () -> {
            profesorServicio.actualizarProfesor(2L, PROFESOR_PETICION);
        });
    }

    @Test
    public void listar() {
        List<ProfesorRespuesta> profesorRespuestas = List.of(new ProfesorRespuesta(), new ProfesorRespuesta());
        when(profesorServicio.listar()).thenReturn(profesorRespuestas);
        assertNotNull(profesorServicio.listar());
    }
}
