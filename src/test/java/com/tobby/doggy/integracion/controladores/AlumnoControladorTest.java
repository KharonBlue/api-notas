package com.tobby.doggy.integracion.controladores;

import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.IniciarSesionPeticion;
import com.tobby.doggy.configuracion.autorizacion.servicios.CustomUserDetailsService;
import com.tobby.doggy.configuracion.jwt.JwtUtils;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AlumnoControladorTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    private HttpEntity<?> entidad;

    @BeforeEach
    void configuracion() {

        ResponseEntity<IniciarSesionPeticion> response = testRestTemplate.postForEntity(
                "/autorizar/iniciar-sesion",
                IniciarSesionPeticion.builder()
                        .nombreUsuario("test@gmail.com")
                        .contrasenia("12345678")
                        .build(),
                IniciarSesionPeticion.class
        );

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("test@gmail.com", "12345678")
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtUtils.generarToken(userDetails.getUsername());

        entidad = new HttpEntity<>(null, crearCabeceraConJwt(jwt));
    }

    private HttpHeaders crearCabeceraConJwt(String jwt) {
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        cabecera.setBearerAuth(jwt);
        return cabecera;
    }

    @Test
    void listarAlumnos() {

        int page = 1;
        int size = 5;
        String url = String.format("/notas/alumno/listar?page=%d&size=%d&sort=name", page, size);

        ResponseEntity<Page<AlumnoRespuesta>> respuesta = testRestTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<AlumnoRespuesta>>() {});

        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        Page<AlumnoRespuesta> pageable = respuesta.getBody();

    }


    @Test
    void actualizarAlumno(){
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(
                "/notas/alumno/actualizar/1",
                HttpMethod.POST,
                entidad,
                String.class
        );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void eliminarAlumno(){
        
    }


    private void crearEntidad(Object objeto, String jwt){
        entidad = new HttpEntity<>(objeto, crearCabeceraConJwt(jwt));
    }

}
