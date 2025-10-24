package com.tobby.doggy.modelado.entidades;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
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
@Table(name = "MATERIA")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATERIA_ID")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREACION")
    private LocalDate creacion;

    @Column(name = "NOMBRE_MATERIA")
    @Enumerated(EnumType.STRING)
    private NombreMateria materia;

    @Column(name = "LISTA_PROFESORES")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "PROFESOR_MATERIA",
            joinColumns = @JoinColumn(name = "MATERIA_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROFESOR_ID"))
    private List<Profesor> profesor;

    @Column(name = "PUNTAJE")
    private double puntaje;

    @Column(name = "APROBADA")
    private boolean aprobada;
}
