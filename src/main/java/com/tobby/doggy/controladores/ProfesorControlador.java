package com.tobby.doggy.controladores;

import com.tobby.doggy.modelado.interfaces.IProfesor;
import com.tobby.doggy.modelado.peticiones.ProfesorPeticion;
import com.tobby.doggy.modelado.respuestas.ProfesorRespuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notas/profesor")
@Tag(name = "Profesor", description = "Operaciones")
@SecurityRequirement(name = "bearerAuth")
public class ProfesorControlador {

    @Autowired
    private IProfesor profesorServicio;

    @PostMapping("/crear")
    @Operation(description = "Recibe un Profesor y retorna una Respuesta con los datos de creacion. Requiere autenticacion",
            tags = {"Profesor"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Recibe un Profesor con su nombre, apellido, email, matricula y materia asignada",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProfesorPeticion.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Creacion exitosa",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProfesorRespuesta.class)
                    )))
    public ResponseEntity<ProfesorRespuesta> crear(@RequestBody ProfesorPeticion profesorPeticion) {
        return ResponseEntity.ok().body(profesorServicio.crearProfesor(profesorPeticion));
    }

    @PutMapping("/actualizar/{id}")
    @Operation(description = "Recibe un Profesor, verifica si existe y retorna una Respuesta con los datos actualizados. Requiere autenticacion",
            tags = {"Profesor"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Recibe un Profesor con su nombre, apellido, email, matricula y materia asignada",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProfesorPeticion.class)
                    )
            ),
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Actualizacion exitosa",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProfesorRespuesta.class)
                    )))
    public ResponseEntity<ProfesorRespuesta> actualizar(@PathVariable Long id, @RequestBody ProfesorPeticion profesorPeticion) {
        return ResponseEntity.ok(profesorServicio.actualizarProfesor(id, profesorPeticion));
    }

    @GetMapping("/listar")
    @Operation(description = "Retorna una lista de Profesores. Requiere autenticacion",
            tags = {"Profesor"},
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Listado exitoso"
                    ))
    public ResponseEntity<List<ProfesorRespuesta>> listar() {
        return ResponseEntity.ok(profesorServicio.listar());
    }
}
