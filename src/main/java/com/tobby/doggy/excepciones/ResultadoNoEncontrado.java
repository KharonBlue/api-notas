package com.tobby.doggy.excepciones;

public class ResultadoNoEncontrado extends RuntimeException{

    public ResultadoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
