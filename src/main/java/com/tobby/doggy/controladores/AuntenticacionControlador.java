package com.tobby.doggy.controladores;

import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.TokenRespuesta;
import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.IniciarSesionPeticion;
import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.RegistroPeticion;
import com.tobby.doggy.configuracion.autorizacion.servicios.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autorizar")
public class AuntenticacionControlador {

    @Autowired
    private CustomUserDetailsService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/registrar")
    public String registrar(@RequestBody RegistroPeticion registroPeticion) {
        return service.registrar(registroPeticion);
    }

    @PostMapping(value = "/iniciar-sesion")
    public TokenRespuesta iniciarSesion(@RequestBody IniciarSesionPeticion iniciarSesionPeticion) {
        return service.iniciarSesion(iniciarSesionPeticion, authenticationManager);
    }
}
