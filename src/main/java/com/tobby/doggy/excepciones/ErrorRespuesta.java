package com.tobby.doggy.excepciones;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorRespuesta {

    private String error;
    private String mensaje;
    private String url;
    private int estado;
    private LocalDate marcarTiempo = LocalDate.now();

    public ErrorRespuesta(String error, String mensaje, String url, int estado) {
        this.error = error;
        this.mensaje = mensaje;
        this.url = url.replace("uri=", "");
        this.estado = estado;
    }
}
