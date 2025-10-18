package com.tobby.doggy.controladores;


import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import com.tobby.doggy.servicios.GestorMaterias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("notas/alumno")
@RestController
public class AlumnoControlador {


    @PostMapping("/crear")
    public AlumnoRespuesta crear(@RequestBody AlumnoPeticion alumnoPeticion){

        return new AlumnoRespuesta();
    }

    @PutMapping("/actualizar/{id}")
    public AlumnoRespuesta actualizar(@PathVariable int id, @RequestBody AlumnoPeticion alumnoPeticion){

        return new AlumnoRespuesta();
    }

    @GetMapping("/listar")
    public List<AlumnoRespuesta> listar(){

        return List.of(new AlumnoRespuesta());
    }
}
