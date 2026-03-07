package com.tobby.doggy.unitarios;

import com.tobby.doggy.mapeadores.MateriaMapeador;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.peticiones.MateriaPeticion;
import com.tobby.doggy.modelado.respuestas.MateriaRespuesta;
import com.tobby.doggy.repositorios.IMateriaRepositorio;
import com.tobby.doggy.servicios.MateriaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MateriaServicioTest {

    @Mock
    private IMateriaRepositorio materiaRepositorio;
    @Mock
    private MateriaMapeador materiaMapeador;
    @InjectMocks
    private MateriaServicio materiaServicio;

    private final Alumno ALUMNO_PREPARADO = new Alumno(1L, LocalDate.now(), "Nombre", "Apellido", null);
    private final List<Materia> MATERIAS_PREPARADAS = List.of(new Materia(
                    1L, LocalDate.now(), NombreMateria.ARTES, 8, true, ALUMNO_PREPARADO),
            new Materia(
                    2L, LocalDate.now(), NombreMateria.LENGUA, 8, true, ALUMNO_PREPARADO));
    private List<MateriaRespuesta> materiaRespuestas = List.of(new MateriaRespuesta(
                    NombreMateria.ARTES, 8, true, null),
            new MateriaRespuesta(
                    NombreMateria.LENGUA, 8, true, null));

    @BeforeEach
    public void crearLista() {
        ALUMNO_PREPARADO.setMaterias(MATERIAS_PREPARADAS);
    }

    @Test
    public void crear() {
        when(materiaRepositorio.saveAll(ALUMNO_PREPARADO.getMaterias())).thenReturn(MATERIAS_PREPARADAS);

        when(materiaServicio.crear(ALUMNO_PREPARADO)).thenReturn(materiaRespuestas);
        List<MateriaRespuesta> resultado = materiaServicio.crear(ALUMNO_PREPARADO);

        assertNotNull(resultado);
        assertFalse(ALUMNO_PREPARADO.getMaterias().isEmpty());
    }

    @Test
    public void actualizar() {
        AlumnoPeticion alumnoPeticion = new AlumnoPeticion("NombreActualizado","ApellidoActualizado", new ArrayList<MateriaPeticion>());
        List<Materia> materiasActualizadas = List.of(new Materia(
                        1L, LocalDate.now(), NombreMateria.ARTES, 4, false, ALUMNO_PREPARADO),
                new Materia(
                        2L, LocalDate.now(), NombreMateria.LENGUA, 9, true, ALUMNO_PREPARADO));

        when(materiaMapeador.actualizar(ALUMNO_PREPARADO, alumnoPeticion)).thenReturn(materiasActualizadas);
        when(materiaRepositorio.saveAll(materiasActualizadas)).thenReturn(materiasActualizadas);
        List<Materia> resultado = materiaServicio.actualizar(ALUMNO_PREPARADO, alumnoPeticion);

        assertNotEquals(true, resultado.get(0).isAprobada());
    }

    @Test
    public void listar(){
        //when(materiaRepositorio.findAll()).thenReturn(MATERIAS_PREPARADAS);
        when(materiaServicio.listar()).thenReturn(materiaRespuestas);
        List<MateriaRespuesta> resultado = materiaServicio.listar();
        assertNotNull(resultado);
    }

}
