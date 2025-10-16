package com.tobby.doggy.modelado.entidades;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Alumno {

    private int id;
    private LocalDate creacion;
    private String nombre;
    private String apellido;
    private List<Materia> materias;
}
