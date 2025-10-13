package com.tobby.doggy.model.entidades;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Profesor {

    private int id;
    private LocalDate creacion;
    private String nombre;
    private String apellido;
    private Integer matricula;
    private String especialidad;
}
