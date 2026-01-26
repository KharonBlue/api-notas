package com.tobby.doggy.modelado.respuestas;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AlumnoRespuesta {

    private LocalDate creacion;
    private String nombre;
    private String apellido;
    private List<MateriaRespuesta> materias;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoRespuesta that = (AlumnoRespuesta) o;
        return Objects.equals(creacion, that.creacion) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(materias, that.materias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creacion, nombre, apellido, materias);
    }
}
