package com.tobby.doggy.integracion;

import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.IUsuarioRepositorio;
import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.Rol;
import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.Usuario;
import com.tobby.doggy.configuracion.jwt.JwtUtils;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import com.tobby.doggy.repositorios.IAlumnoRepositorio;
import com.tobby.doggy.servicios.AlumnoServicio;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;
import java.util.Random;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AlumnoIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private IAlumnoRepositorio alumnoRepositorio;

    @MockitoBean
    IUsuarioRepositorio usuarioRepositorio;
    @MockitoBean
    AuthenticationManager authenticationManager;

    HttpHeaders headers = new HttpHeaders();

    final Usuario USUARIO = new Usuario(1L,"admin", "admin", Rol.ADMIN);

    @Test
    public void listarAlumnos() throws Exception {
        mockMvc.perform(get("/notas/alumno/listar")
                        .with(user(USUARIO.getUsername()).password(USUARIO.getPassword()).roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminPuedeCrearAlumno() throws Exception {
        mockMvc.perform(post("/notas/alumno/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "nombre" : "Facundo",
                                "apellido" : "Castro"
                                }
                                """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Facundo"));
    }

    @Test
    @Disabled
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminPuedeEliminarAlumno() throws Exception {

        Long id = 1L;
        Mockito.when(alumnoRepositorio.existsById(id)).thenReturn(true);

        mockMvc.perform(delete("/notas/alumno/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void obtenerAlumnos(){

        int page = 1;
        int size = 5;
        String url = String.format("http://localhost:8080/notas/alumno/listar?page=%d&size=%d&sort=name", page, size);

        ResponseEntity<Page<AlumnoRespuesta>> respuesta = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<AlumnoRespuesta>>() {});

        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        Page<AlumnoRespuesta> pageable = respuesta.getBody();
    }


    void crearUsuario(){
        Mockito.when(usuarioRepositorio.save(USUARIO)).thenReturn(USUARIO);
    }


}
