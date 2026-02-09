package com.tobby.doggy.controladores;

import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import com.tobby.doggy.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notas/profesor")
public class ProfesorControlador {

    @Autowired
    private ProfesorServicio profesorServicio;

    @PostMapping("/crear")
    public ResponseEntity<ProfesorRespuesta> crear(@RequestBody ProfesorPeticion profesorPeticion) {
        return ResponseEntity.ok().body(profesorServicio.crearProfesor(profesorPeticion));
    }

    @PutMapping("/actualizar/{id}")
    public HttpStatus actualizar(@PathVariable int id, @RequestBody ProfesorPeticion profesorPeticion) {

        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/listar")
    public List<ProfesorRespuesta> listar() {

        return List.of(new ProfesorRespuesta());
    }
}
