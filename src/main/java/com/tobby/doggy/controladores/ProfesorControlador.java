package com.tobby.doggy.controladores;

import com.tobby.doggy.modelado.interfaces.IProfesor;
import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notas/profesor")
public class ProfesorControlador {

    @Autowired
    private IProfesor profesorServicio;

    @PostMapping("/crear")
    public ResponseEntity<ProfesorRespuesta> crear(@RequestBody ProfesorPeticion profesorPeticion) {
        return ResponseEntity.ok().body(profesorServicio.crearProfesor(profesorPeticion));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProfesorRespuesta> actualizar(@PathVariable Long id, @RequestBody ProfesorPeticion profesorPeticion) {
        return ResponseEntity.ok(profesorServicio.actualizarProfesor(id, profesorPeticion));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProfesorRespuesta>> listar() {
        return ResponseEntity.ok(profesorServicio.listar());
    }
}
