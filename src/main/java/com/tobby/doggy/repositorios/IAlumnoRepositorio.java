package com.tobby.doggy.repositorios;

import com.tobby.doggy.modelado.entidades.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlumnoRepositorio extends JpaRepository<Alumno, Long> {
}
