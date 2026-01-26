package com.tobby.doggy.controladores;


import com.tobby.doggy.modelado.interfaces.IAlumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("notas/alumno")
@RestController
@Slf4j
public class AlumnoControlador {

    @Autowired
    private IAlumno alumnoServicio;

    @PostMapping("/crear")
    public AlumnoRespuesta crear(@RequestBody AlumnoPeticion alumnoPeticion) {
        log.info("Creacion del Alumno={}", alumnoPeticion.getNombre());
        return alumnoServicio.crear(alumnoPeticion);
    }

    @PutMapping("/actualizar/{id}")
    public AlumnoRespuesta actualizar(@PathVariable Long id, @RequestBody AlumnoPeticion alumno) {
        log.debug("Actualizando alumno={} {}", id, alumno.getNombre());
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
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Eliminando usuario={}", id);
        return ResponseEntity.status(204).body(alumnoServicio.eliminar(id));
    }
}
