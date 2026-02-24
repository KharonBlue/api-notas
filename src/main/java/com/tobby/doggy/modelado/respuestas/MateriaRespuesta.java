package com.tobby.doggy.modelado.respuestas;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Materia registrada en el sistema")
public class MateriaRespuesta {

    @Schema(description = "Enumerado con el nombre de la materia")
    private NombreMateria nombreMateria;
    private double puntaje;
    private boolean aprobada;
    @Schema(description = "Profesor asignado a la materia")
    private ProfesorRespuesta profesorRespuesta;
}
