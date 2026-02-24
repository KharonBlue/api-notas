package com.tobby.doggy.modelado.entidades;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PROFESOR")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFESOR_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREACION")
    private LocalDate creacion;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "MATRICULA", unique = true)
    private Integer matricula;

    @Column(name = "MATERIA")
    private NombreMateria materia;

    public Profesor(String nombre, String apellido, String email, Integer matricula, NombreMateria materia, LocalDate creacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.matricula = matricula;
        this.materia = materia;
        this.creacion = creacion;
    }

    public Profesor(String nombre, String apellido, String email, Integer matricula, NombreMateria materia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.matricula = matricula;
        this.materia = materia;
    }
}
