package com.tobby.doggy.modelado.respuestas;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MateriaRespuesta {

    private NombreMateria nombreMateria;
    private double puntaje;
    private boolean aprobada;
}
