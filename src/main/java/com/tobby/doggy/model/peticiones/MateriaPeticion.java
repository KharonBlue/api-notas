package com.tobby.doggy.model.peticiones;

import com.tobby.doggy.model.entidades.Profesor;
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
