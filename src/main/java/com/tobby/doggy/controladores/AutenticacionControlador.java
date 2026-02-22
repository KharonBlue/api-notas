package com.tobby.doggy.controladores;

import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.TokenRespuesta;
import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.IniciarSesionPeticion;
import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.RegistroPeticion;
import com.tobby.doggy.configuracion.autorizacion.servicios.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autorizar")
@Slf4j
@Tag(name = "Autenticacion", description = "Controlador para la autenticación")
public class AutenticacionControlador {

    @Autowired
    private CustomUserDetailsService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/registrar")
    @Operation(
            summary = "Registro de usuario",
            description = "Registra un usuario un mensaje de confirmacion del registro",
            tags = {"Autenticacion"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Peticion del registro con nombre de usuario y contraseña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegistroPeticion.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario registrado"
                            //, content() colocar la respuesta que otorga
                    )
            }
    )
    public String registrar(
            @Parameter(description = "Peticion del registro con nombre de usuario y contraseña")
            @RequestBody RegistroPeticion registroPeticion) {
        log.debug("Registrando usuario={}", registroPeticion.getNombreUsuario());
        return service.registrar(registroPeticion);
    }


    @Operation(
            summary = "Inicia sesión de un usuario",
            description = "Si el usuario está registrado retorna un token de acceso",
            tags = {"Autenticacion"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Peticion del inicio de sesión con nombre de usuario y contraseña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegistroPeticion.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Inicio de sesión exitoso"
                            , content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TokenRespuesta.class)
                    ))
            })

    @PostMapping(value = "/iniciar-sesion")
    public TokenRespuesta iniciarSesion(@RequestBody IniciarSesionPeticion iniciarSesionPeticion) {
        log.debug("Iniciando sesion usuario={}", iniciarSesionPeticion.getNombreUsuario());
        return service.iniciarSesion(iniciarSesionPeticion, authenticationManager);
    }
}
