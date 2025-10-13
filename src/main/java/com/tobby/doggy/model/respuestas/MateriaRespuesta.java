package com.tobby.doggy.model.respuestas;

import com.tobby.doggy.model.entidades.Profesor;
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
