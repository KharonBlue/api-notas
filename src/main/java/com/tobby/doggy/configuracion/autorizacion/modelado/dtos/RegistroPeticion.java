package com.tobby.doggy.configuracion.autorizacion.modelado.dtos;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Peticion del registro", nullable = false)
public class RegistroPeticion {

    @Parameter(name = "Nombre de usuario", required = true)
    String nombreUsuario;
    @Parameter(name = "Contraseña", required = true)
    String contrasenia;
}
