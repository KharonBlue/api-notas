package com.tobby.doggy.configuracion.autorizacion.servicios.mapeadores;

import com.tobby.doggy.configuracion.autorizacion.modelado.dtos.RegistroPeticion;
import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.Rol;
import com.tobby.doggy.configuracion.autorizacion.modelado.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapeador {
    
    public Usuario registrar(RegistroPeticion registroPeticion, String contraseniaEncriptada) {
        return new Usuario(
                null,
                registroPeticion.getNombreUsuario(),
                contraseniaEncriptada,
                Rol.USER);
    }

}
