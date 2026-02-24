package com.tobby.doggy.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SolicitudIncorrecta extends RuntimeException{

    public SolicitudIncorrecta(String mensaje) {
        super(mensaje);

    }
}
