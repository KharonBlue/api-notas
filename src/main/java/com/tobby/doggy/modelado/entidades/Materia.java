package com.tobby.doggy.modelado.entidades;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "MATERIA")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATERIA_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREACION")
    private LocalDate creacion;

    @Column(name = "NOMBRE_MATERIA")
    @Enumerated(EnumType.STRING)
    private NombreMateria nombreMateria;

    @Column(name = "PUNTAJE")
    private double puntaje;

    @Column(name = "APROBADA")
    private boolean aprobada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ALUMNO")
    private Alumno alumno;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "FK_PROFESOR",
            joinColumns = @JoinColumn(name = "MATERIA_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROFESOR_ID"))
    private Set<Profesor> profesores = new LinkedHashSet<>();

    @Column(name = "PROFESOR_ASIGNADO", unique = true)
    @JdbcTypeCode(SqlTypes.JSON)
    private Profesor profesorAsignado;

    public Materia(NombreMateria nombreMateria, LocalDate creacion) {
        this.nombreMateria = nombreMateria;
        this.creacion = creacion;
    }

    public Materia(LocalDate creacion, NombreMateria materia, double puntaje, boolean aprobada, Alumno alumno, Set<Profesor> profesores) {
        this.creacion = creacion;
        this.nombreMateria = materia;
        this.puntaje = puntaje;
        this.aprobada = aprobada;
        this.alumno = alumno;
        this.profesores = profesores;
    }

    public Materia(NombreMateria nombreMateria, double puntaje, boolean aprobada) {
        this.nombreMateria = nombreMateria;
        this.puntaje = puntaje;
        this.aprobada = aprobada;
    }
}