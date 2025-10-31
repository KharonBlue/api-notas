package com.tobby.doggy.modelado.entidades;

import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

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

    public Materia(LocalDate creacion, NombreMateria materia, double puntaje, boolean aprobada) {
        this.creacion = creacion;
        this.nombreMateria = materia;
        this.puntaje = puntaje;
        this.aprobada = aprobada;
    }
}