package com.tobby.doggy.controladores;


import com.tobby.doggy.modelado.interfaces.IAlumno;
import com.tobby.doggy.modelado.peticiones.AlumnoPeticion;
import com.tobby.doggy.modelado.respuestas.AlumnoRespuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("notas/alumno")
@RestController
@Slf4j
@Tag(name = "Alumno", description = "Operaciones relacionadas con la gestion de alumnos")
@SecurityRequirement(name = "bearerAuth")
public class AlumnoControlador {

    @Autowired
    private IAlumno alumnoServicio;

    @PostMapping("/crear")
    @Operation(
            description = "Recibe un alumno con su nombre y apellido, retorna una respuesta con los datos de creacion y las materias asignadas. Requiere autenticacion",
            tags = {"Alumno"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Peticion de creacion con el nombre y apellido",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlumnoPeticion.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Creacion exitosa"
                            , content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlumnoRespuesta.class)
                    ))
            })
    public AlumnoRespuesta crear(@RequestBody AlumnoPeticion alumnoPeticion) {
        log.info("Creacion del Alumno={}", alumnoPeticion.getNombre());
        return alumnoServicio.crear(alumnoPeticion);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(
            description = "Actualiza un alumno y su lista de materias, retorna una respuesta con los datos y materias actualizadas. Requiere autenticacion",
            tags = {"Alumno"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Peticion de actualizacion con el nombre, apellido y lista de materias con sus datos",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlumnoPeticion.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Actualizacion exitosa"
                            , content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlumnoRespuesta.class)
                    ))
            })
    public AlumnoRespuesta actualizar(@PathVariable Long id, @RequestBody AlumnoPeticion alumno) {
        log.debug("Actualizando alumno={} {}", id, alumno.getNombre());
        return alumnoServicio.actualizar(id, alumno);
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Obtiene alumnos paginados",
            description = "Retorna una lista paginada ordenados por nombre ascendente. Requiere autenticación",
            tags = {"Alumno"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado exitoso"
                            , content = @Content(
                            mediaType = "application/json"
                    ))
            })
    public ResponseEntity<Page<AlumnoRespuesta>> listar(
            @Parameter(description = "Número de página (base 0)", example = "0")
            @RequestParam(defaultValue = "0") int pagina,
            @Parameter(description = "Cantidad de elementos por página", example = "10")
            @RequestParam(defaultValue = "10") int tamanio,
            @Parameter(
                    description = "Campo y dirección de orden",
                    example = "nombre,asc",
                    array = @ArraySchema(schema = @Schema(type = "string"))
            )
            @RequestParam(defaultValue = "nombre,asc") String[] orden) {
        return ResponseEntity.ok(alumnoServicio.listar(orden, tamanio, pagina));
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(
            description = "Recibe un id, verifica su existencia, elimina el alumno si existe y retorna un mensaje. Requiere autenticacion",
            tags = {"Alumno"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Peticion del id",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Long.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Eliminacion exitosa"
                            , content = @Content(
                            mediaType = "application/json"
                    ))
            })
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Eliminando usuario={}", id);
        return ResponseEntity.status(204).body(alumnoServicio.eliminar(id));
    }
}
