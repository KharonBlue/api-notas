package com.tobby.doggy.excepciones;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ManejoGlobalDeErrores {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespuesta> handleException(IdNoEncontrado ex) {
        ErrorRespuesta respuesta = new ErrorRespuesta();
        respuesta.setError(HttpStatus.NOT_FOUND.toString());
        respuesta.setMensaje(ex.getMessage());
        respuesta.setEstado(HttpStatus.NOT_FOUND.value());
        respuesta.setMarcarTiempo(LocalDate.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }


}
