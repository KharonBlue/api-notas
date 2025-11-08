package com.tobby.doggy.controladores;


import com.tobby.doggy.modelado.interfaces.IAlumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("notas/alumno")
@RestController
public class AlumnoControlador {

    @Autowired
    private IAlumno alumnoServicio;

    @PostMapping("/crear")
    public AlumnoRespuesta crear(@RequestBody AlumnoPeticion alumnoPeticion) {
        return alumnoServicio.crear(alumnoPeticion);
    }

    @PutMapping("/actualizar/{id}")
    public AlumnoRespuesta actualizar(@PathVariable Long id, @RequestBody AlumnoPeticion alumno) {
        return alumnoServicio.actualizar(id, alumno);
    }

    @GetMapping("/listar")
    public List<AlumnoRespuesta> listar() {
        return alumnoServicio.listar();
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        return alumnoServicio.eliminar(id);
    }
}
