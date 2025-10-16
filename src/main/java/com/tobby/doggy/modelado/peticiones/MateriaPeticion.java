package com.tobby.doggy.modelado.peticiones;

import com.tobby.doggy.modelado.entidades.Profesor;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MateriaPeticion {

    private String nombre;
    private double puntaje;
    private boolean aprobada;
    private Profesor profesor;
}
