package com.tobby.doggy.modelado.peticiones;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlumnoPeticion {

    private String nombre;
    private String apellido;
    private List<MateriaPeticion> materias;

    public AlumnoPeticion(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
