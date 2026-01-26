package com.tobby.doggy.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ManejoGlobalDeErrores {

    /*Al usar la etiqueta @Valid en el controlador capturaremos los errores de
    validacion aplicados en la entidad y los mostraremos a traves de un JSON*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> capturarMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        Map<String, String> mapaDeErrores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String clave = ((FieldError) error).getField();
            String valor = error.getDefaultMessage();
            mapaDeErrores.put(clave, valor);
        });

        ErrorRespuesta respuesta = new ErrorRespuesta(HttpStatus.BAD_REQUEST.toString(),
                mapaDeErrores.toString(),
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    //Capturo el error a la hora de buscar un ID especifico inexistente
    @ExceptionHandler(IdNoEncontrado.class)
    public ResponseEntity<ErrorRespuesta> capturarIdNoEncontradoExcepcion(IdNoEncontrado ex, WebRequest webRequest) {

        ErrorRespuesta respuesta = new ErrorRespuesta(HttpStatus.NOT_FOUND.toString(),
                ex.getMessage(),
                webRequest.getDescription(false), //Para obtener la url sin incluir información del cliente
                HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(SolicitudIncorrecta.class)
    public ResponseEntity<ErrorRespuesta> capturarSolicitudIncorrectaExcepcion(SolicitudIncorrecta ex, WebRequest webRequest) {

        ErrorRespuesta respuesta = new ErrorRespuesta(HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(),
                webRequest.getDescription(false), //Para obtener la url sin incluir información del cliente
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespuesta> capturarExcepcion(Exception ex, WebRequest webRequest) {

        ErrorRespuesta respuesta = new ErrorRespuesta(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage(),
                webRequest.getDescription(false), //Para obtener la url sin incluir información del cliente
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }


}
