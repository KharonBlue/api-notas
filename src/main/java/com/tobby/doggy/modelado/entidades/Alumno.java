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
@Table(name = "ALUMNO")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALUMNO_ID", unique = true)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREACION")
    private LocalDate creacion;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "LISTA_MATERIAS")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "ALUMNO_MATERIA",
            joinColumns = @JoinColumn(name = "ALUMNO_ID"),
            inverseJoinColumns = @JoinColumn(name = "MATERIA_ID"))
    private List<Materia> materias;
}
