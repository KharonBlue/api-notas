package com.tobby.doggy.configuracion.autorizacion.modelado.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IniciarSesionPeticion {
    String nombreUsuario;
    String contrasenia;
}
