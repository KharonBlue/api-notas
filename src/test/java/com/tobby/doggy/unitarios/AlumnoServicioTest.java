package com.tobby.doggy.unitarios;

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

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    public Alumno ALUMNO_PREPARADO = new Alumno(1L,LocalDate.now(),"Nombre","Apellido", null);

    public AlumnoPeticion ALUMNO_PETICION = new AlumnoPeticion("Nombre", "Apellido", null);

    public  AlumnoRespuesta ALUMNO_ESPERADO = new AlumnoRespuesta(LocalDate.now(), "Nombre", "Apellido", null);

    @Test
    public void crearUsuario() {
        //Seteo las materias a los resultados
        ALUMNO_PREPARADO.setMaterias(materiaMapeador.crear(ALUMNO_PREPARADO));
        ALUMNO_ESPERADO.setMaterias(materiaServicio.crear(ALUMNO_PREPARADO));

        //Cuando creo un usuario (ALUMNO_PETICION) este retornara el ALUMNO_ESPERADO
        when(alumnoServicio.crear(ALUMNO_PETICION)).thenReturn(ALUMNO_ESPERADO);

        //LLamo a la función crear para obtener el un AlumnoRespuesta
        alumnoServicio.crear(ALUMNO_PETICION);

        //AFIRMAMOS que los valores sean iguales entre el obtenido y el esperado
        assertEquals("Nombre", ALUMNO_ESPERADO.getNombre());
        assertEquals("Apellido", ALUMNO_ESPERADO.getApellido());
        assertEquals(materiaServicio.crear(ALUMNO_PREPARADO), ALUMNO_ESPERADO.getMaterias());
    }

    


}
