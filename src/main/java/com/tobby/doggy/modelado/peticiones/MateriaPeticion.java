package com.tobby.doggy.modelado.peticiones;

import com.tobby.doggy.modelado.entidades.Profesor;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MateriaPeticion {

    private String nombre;
    private double puntaje;
    private boolean aprobada;
}
