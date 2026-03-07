package com.tobby.doggy.integracion.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tobby.doggy.mapeadores.AlumnoMapeador;
import com.tobby.doggy.modelado.entidades.Alumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.servicios.AlumnoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AlumnoControladorTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IAlumnoRepositorio alumnoRepositorio;
    @MockitoBean
    private AlumnoServicio alumnoServicio;
    @Autowired
    private AlumnoMapeador alumnoMapeador;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void crearAlumno() throws Exception{
        AlumnoPeticion alumnoPeticion = new AlumnoPeticion();
        alumnoPeticion.setNombre("Nombre");
        alumnoPeticion.setApellido("Apellido");

        mockMvc.perform(post("/notas/alumno/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoPeticion))
                )
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void listarAlumnos() throws Exception{
        mockMvc.perform(get("/notas/alumno/listar")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void actualizar() throws Exception {

        Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
        alumno.setApellido("Perez");
        alumno = alumnoRepositorio.save(alumno);

        AlumnoPeticion alumnoPeticion = new AlumnoPeticion();
        alumnoPeticion.setNombre("NombreActualizado");
        alumnoPeticion.setApellido("ApellidoActualizado");


         mockMvc.perform(put("/notas/alumno/actualizar/{id}", alumno.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoPeticion))
                )
                .andDo(print())
                .andExpect(status().isOk());
                // .andExpect(jsonPath("$.nombre").value("NombreActualizado"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void eliminar() throws Exception {
        mockMvc.perform(delete("/notas/alumno/eliminar/{id}", 1L))
                .andExpect(status().isNoContent());

        assertFalse(alumnoRepositorio.existsById(1L));
    }


}
