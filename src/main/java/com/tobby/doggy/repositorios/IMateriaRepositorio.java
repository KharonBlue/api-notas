package com.tobby.doggy.repositorios;

import com.tobby.doggy.modelado.entidades.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMateriaRepositorio extends JpaRepository<Materia, Long> {

    Optional<Materia> findByNombreMateria(String nombre);


}
