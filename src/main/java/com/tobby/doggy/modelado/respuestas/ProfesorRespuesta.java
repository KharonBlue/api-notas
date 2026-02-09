package com.tobby.doggy.modelado.respuestas;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfesorRespuesta {

    private String nombre;
    private String apellido;
    private String email;
    private Integer matricula;
    private NombreMateria materia;
    private LocalDate creacion;
}
