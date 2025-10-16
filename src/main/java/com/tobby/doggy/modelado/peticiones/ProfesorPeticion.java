package com.tobby.doggy.modelado.peticiones;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfesorPeticion {

    private String nombre;
    private String apellido;
    private Integer matricula;
    private String especialidad;
}
