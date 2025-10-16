package com.tobby.doggy.modelado.respuestas;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfesorRespuesta {

    private LocalDate creacion;
    private String nombre;
    private String apellido;
    private String especialidad;
}
