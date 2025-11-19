package com.tobby.doggy.controladores;


import com.tobby.doggy.modelado.interfaces.IAlumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<AlumnoRespuesta>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "nombre,asc") String[] orden) {
        return ResponseEntity.ok(alumnoServicio.listar(orden, tamanio, pagina));
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        return alumnoServicio.eliminar(id);
    }
}
