package com.tobby.doggy.modelado.peticiones;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfesorPeticion {

    private String nombre;
    private String apellido;
    private String email;
    private Integer matricula;
    private NombreMateria materia;
}
