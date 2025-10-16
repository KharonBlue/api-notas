package com.tobby.doggy.modelado.entidades;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Materia {

    private int id;
    private LocalDate creacion;
    private String nombre;
    private double puntaje;
    private boolean aprobada;
    private Profesor profesor;
}
