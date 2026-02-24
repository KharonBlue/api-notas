package com.tobby.doggy.repositorios;

import com.tobby.doggy.modelado.entidades.Profesor;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IProfesorRepositorio extends JpaRepository<Profesor,Long> {

    @Query("SELECT p FROM Profesor p WHERE p.materia= :nombreMateria")
    Set<Profesor> findByMateria(@Param("nombreMateria") NombreMateria nombreMateria);
}
