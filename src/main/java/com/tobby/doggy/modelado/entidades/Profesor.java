package com.tobby.doggy.modelado.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @Column(name = "LISTA_MATERIAS")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "MATERIA_PROFESOR",
            joinColumns = @JoinColumn(name = "PROFESOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "MATERIA_ID"))
    private List<Materia> materias;

    @Column(name = "LISTA_ALUMNOS")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "ALUMNO_PROFESOR",
            joinColumns = @JoinColumn(name = "PROFESOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "ALUMNO_ID"))
    private List<Alumno> alumnos;

    public Profesor(String nombre, String apellido, String email, Integer matricula, List<Materia> materias, List<Alumno> alumnos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.matricula = matricula;
        this.materias = materias;
        this.alumnos = alumnos;
    }
}
