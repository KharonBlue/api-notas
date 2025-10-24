package com.tobby.doggy.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorRespuesta {

    private String error;
    private String mensaje;
    private int estado;
    private LocalDate marcarTiempo;
}
