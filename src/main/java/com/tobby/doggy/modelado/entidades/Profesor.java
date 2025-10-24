package com.tobby.doggy.modelado.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private int id;

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

}
