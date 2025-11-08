package com.tobby.doggy.modelado.peticiones;

import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MateriaPeticion {

    private NombreMateria nombre;
    private double puntaje;
    private boolean aprobada;
}
