package com.tobby.doggy.modelado.respuestas;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Profesor registrado en el sistema")
public class ProfesorRespuesta {

    private String nombre;
    private String apellido;
    private String email;
    private Integer matricula;
    @Schema(description = "Enumerado con el nombre de la materia", example = "LENGUA")
    private NombreMateria materia;
    private LocalDate creacion;
}
