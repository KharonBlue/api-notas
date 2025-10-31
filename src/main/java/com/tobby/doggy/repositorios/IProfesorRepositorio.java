package com.tobby.doggy.repositorios;

import com.tobby.doggy.modelado.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfesorRepositorio extends JpaRepository<Profesor,Long> {
}
