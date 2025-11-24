package com.tobby.doggy.configuracion.autorizacion.modelado.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPeticion {
    String nombreUsuario;
    String contrasenia;
}
