package com.tobby.doggy.servicios;

import com.tobby.doggy.modelado.entidades.Materia;
import com.tobby.doggy.modelado.entidades.enumerados.NombreMateria;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestorMaterias {

    private List<Materia> materias = new ArrayList<>();

    public GestorMaterias() {
        crearListaMaterias();
    }

    public void crear(Materia materia, NombreMateria nombreMateria) {
        materia.setId(materias.size() + 1);
        materia.setPuntaje(0);
        materia.setAprobada(false);
        materia.setCreacion(LocalDate.now());
        materia.setMateria(nombreMateria);
        materias.add(materia);
    }

    public List<Materia> listar() {
        return materias;
    }

    private void crearListaMaterias() {
        crear(new Materia(), NombreMateria.LENGUA);
        crear(new Materia(), NombreMateria.ARTES);
        crear(new Materia(), NombreMateria.ED_FISICA);
        crear(new Materia(), NombreMateria.MATEMATICAS);
        crear(new Materia(), NombreMateria.NATURALEZA);
    }
}
