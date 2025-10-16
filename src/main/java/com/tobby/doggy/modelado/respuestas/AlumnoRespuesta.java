package com.tobby.doggy.modelado.respuestas;

import com.tobby.doggy.modelado.entidades.Materia;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AlumnoRespuesta {

    private LocalDate creacion;
    private String nombre;
    private String apellido;
    private List<Materia> materias;
}
