package com.tobby.doggy.modelado.entidades;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
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
    private NombreMateria materia;
    private double puntaje;
    private boolean aprobada;
}
