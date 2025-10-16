package com.tobby.doggy.modelado.respuestas;

import com.tobby.doggy.modelado.entidades.Profesor;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MateriaRespuesta {

    private LocalDate creacion;
    private String nombre;
    private double puntaje;
    private boolean aprobada;
    private Profesor profesor;
}
