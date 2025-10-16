package com.tobby.doggy.modelado.peticiones;

import com.tobby.doggy.modelado.entidades.Materia;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlumnoPeticion {

    private String nombre;
    private String apellido;
    private List<Materia> materias;
}
