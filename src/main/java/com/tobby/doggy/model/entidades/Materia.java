package com.tobby.doggy.model.entidades;

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
    private String descripcion;
    private Profesor profesor;
}
