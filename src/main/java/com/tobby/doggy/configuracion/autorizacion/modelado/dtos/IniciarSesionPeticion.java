package com.tobby.doggy.configuracion.autorizacion.modelado.dtos;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Peticion del inicio de sesion", nullable = false)
public class IniciarSesionPeticion {

    @Parameter(name = "Nombre de usuario", required = true, example = "usuarioUnico1")
    String nombreUsuario;
    @Parameter(name = "Contraseña", required = true)
    String contrasenia;
}
